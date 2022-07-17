package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.ResumeDTO;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.Education;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
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

    @Transactional
    public ResumeDTO save(ResumeDTO resumeRequest) {
        Resume resume = this.dtoMapper.mapModel(resumeRequest, Resume.class);
        Resume savedResume = this.resumeRepository.save(resume);
        return this.dtoMapper.mapModel(savedResume, ResumeDTO.class);
    }

    @Transactional
    public ResumeDTO update(ResumeDTO resumeRequest, Long id) {
        Optional<Resume> resumeOptional = this.resumeRepository.findById(id);
        if (resumeOptional.isPresent()) {
            Resume currentResume = resumeOptional.get();
            this.updateResumeObjectWithRelations(currentResume, resumeRequest);
            return this.dtoMapper.mapModel(currentResume, ResumeDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume bulunamadi!");
        }
    }

    @Transactional(readOnly = true)
    public ResumeDTO find() {
        Resume resume = this.resumeRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume bulunamadi"));
        return this.dtoMapper.mapModel(resume, ResumeDTO.class);
    }

    private void updateResumeObjectWithRelations(Resume currentResume, ResumeDTO resumeRequest) {
        ObjectUpdaterUtil.updateObject(currentResume, resumeRequest, Arrays.asList("id", "workExperiences", "educations"));
        List<WorkExperienceDTO> workExperiences = this.workExperienceService.saveAll(resumeRequest.getWorkExperiences());
        List<WorkExperience> savedWorkExperience = this.dtoMapper.mapListModel(workExperiences, WorkExperience.class);
        currentResume.setWorkExperiences(savedWorkExperience);
        List<EducationDTO> educations = this.educationService.saveAll(resumeRequest.getEducations());
        List<Education> savedEducation = this.dtoMapper.mapListModel(educations, Education.class);
        currentResume.setEducations(savedEducation);
    }
}
