package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.repository.main.WorkExperienceRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public List<WorkExperienceDTO> findAll() {
        List<WorkExperience> workExperienceList = this.workExperienceRepository.findAll();
        if (workExperienceList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Herhangi bir Work Experience bulunamadi.");
        } else {
            return this.dtoMapper.mapListModel(workExperienceList, WorkExperienceDTO.class);
        }
    }

    @Transactional
    public WorkExperienceDTO save(WorkExperienceDTO workExperienceRequest) {
        WorkExperience upToDateWorkExperience = this.dtoMapper.mapModel(workExperienceRequest, WorkExperience.class);
        WorkExperience savedWorkExperience = this.workExperienceRepository.save(upToDateWorkExperience);
        return this.dtoMapper.mapModel(savedWorkExperience, WorkExperienceDTO.class);
    }

    @Transactional
    public List<WorkExperienceDTO> saveAll(List<WorkExperienceDTO> workExperiencesRequest) {
        List<WorkExperience> upToDateWorkExperiences = this.dtoMapper.mapListModel(workExperiencesRequest, WorkExperience.class);
        List<WorkExperience> savedWorkExperiences = this.workExperienceRepository.saveAll(upToDateWorkExperiences);
        return this.dtoMapper.mapListModel(savedWorkExperiences, WorkExperienceDTO.class);
    }


    @Transactional
    public WorkExperienceDTO update(WorkExperienceDTO workExperienceRequest, Long workExperienceId) {
        Optional<WorkExperience> workExperienceOptional = this.workExperienceRepository.findById(workExperienceId);
        if (workExperienceOptional.isPresent()) {
            WorkExperience currentWorkExperience = workExperienceOptional.get();
            WorkExperience upToDateWorkExperience = this.dtoMapper.mapModel(workExperienceRequest, WorkExperience.class);
            ObjectUpdaterUtil.updateObject(currentWorkExperience, upToDateWorkExperience, Arrays.asList("id"));
            WorkExperience savedCurrentWorkExperience = this.workExperienceRepository.save(currentWorkExperience);
            return this.dtoMapper.mapModel(savedCurrentWorkExperience, WorkExperienceDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu id ile work experience bulunamadı.");
        }
    }

}
