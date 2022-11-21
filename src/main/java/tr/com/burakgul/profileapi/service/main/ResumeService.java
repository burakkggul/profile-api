package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
import tr.com.burakgul.profileapi.model.dto.main.ResumeDTO;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.Education;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.repository.main.ResumeRepository;
import tr.com.burakgul.profileapi.model.entity.main.Resume;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final WorkExperienceService workExperienceService;
    private final EducationService educationService;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public ResumeDTO findResume() {
        Resume resume = this.resumeRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume bulunamadi"));
        return this.dtoMapper.mapModel(resume, ResumeDTO.class);
    }

    @Transactional
    public ResumeDTO save(ResumeDTO resumeRequest) {
        Resume resumeToBeSavedOrUpdated = this.dtoMapper.mapModel(resumeRequest, Resume.class);
        this.setResumeWithRelations(resumeToBeSavedOrUpdated, resumeRequest);
        Resume savedResume = this.resumeRepository.save(resumeToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedResume, ResumeDTO.class);
    }

    @Transactional
    public ResumeDTO update(ResumeDTO resumeRequest) {
        Optional<Resume> resumeOptional = this.resumeRepository.findTopByOrderByIdDesc();
        if (resumeOptional.isPresent()) {
            Resume resumeToBeSavedOrUpdated = resumeOptional.get();
            this.updateResumeWithRelations(resumeToBeSavedOrUpdated, resumeRequest);
            Resume savedResume = this.resumeRepository.save(resumeToBeSavedOrUpdated);
            return this.dtoMapper.mapModel(savedResume, ResumeDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume bulunamadi!");
        }
    }

    private void updateResumeWithRelations(Resume resumeToBeSavedOrUpdated, ResumeDTO resumeRequest) {
        Resume upToDateResume = this.dtoMapper.mapModel(resumeRequest, Resume.class);
        ObjectUpdaterUtil.updateObject(resumeToBeSavedOrUpdated, upToDateResume, Arrays.asList("id", "workExperiences", "educations"));
        this.setResumeWithRelations(resumeToBeSavedOrUpdated, resumeRequest);
    }

    private void setResumeWithRelations(Resume resumeToBeSavedOrUpdated, ResumeDTO resumeRequest){
        List<WorkExperienceDTO> savedWorkExperiences = this.workExperienceService.saveAll(resumeRequest.getWorkExperiences());
        List<WorkExperience> mappedWorkExperiencesToSetResume = this.dtoMapper.mapListModel(savedWorkExperiences, WorkExperience.class);
        resumeToBeSavedOrUpdated.setWorkExperiences(mappedWorkExperiencesToSetResume);

        List<EducationDTO> savedEducations = this.educationService.saveAll(resumeRequest.getEducations());
        List<Education> mappedEducationsToSetResume = this.dtoMapper.mapListModel(savedEducations, Education.class);
        resumeToBeSavedOrUpdated.setEducations(mappedEducationsToSetResume);
    }
}
