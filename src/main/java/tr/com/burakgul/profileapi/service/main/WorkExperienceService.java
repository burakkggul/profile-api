package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.repository.main.WorkExperienceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final DTOMapper dtoMapper;

    public WorkExperienceDTO save(WorkExperienceDTO workExperienceRequest) {
        WorkExperience upToDateWorkExperience = this.dtoMapper.mapModel(workExperienceRequest, WorkExperience.class);
        WorkExperience savedWorkExperience = this.workExperienceRepository.save(upToDateWorkExperience);
        return this.dtoMapper.mapModel(savedWorkExperience, WorkExperienceDTO.class);
    }

    public List<WorkExperienceDTO> saveAll(List<WorkExperienceDTO> workExperiencesRequest) {
        List<WorkExperience> upToDateWorkExperiences = this.dtoMapper.mapListModel(workExperiencesRequest, WorkExperience.class);
        List<WorkExperience> workExperiences = this.workExperienceRepository.saveAll(upToDateWorkExperiences);
        return dtoMapper.mapListModel(workExperiences, WorkExperienceDTO.class);
    }

    public WorkExperienceDTO update(WorkExperienceDTO workExperienceRequest, Long workExperienceId) {
        Optional<WorkExperience> workExperienceOptional = this.workExperienceRepository.findById(workExperienceId);
        if (workExperienceOptional.isPresent()) {
            WorkExperience currentWorkExperience = workExperienceOptional.get();
            ObjectUpdaterUtil.updateObject(currentWorkExperience, workExperienceRequest, Arrays.asList("id"));
            WorkExperience savedCurrentWorkExperience = this.workExperienceRepository.save(currentWorkExperience);
            return this.dtoMapper.mapModel(savedCurrentWorkExperience, WorkExperienceDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu id ile work experience bulunamadı.");
        }
    }

    public List<WorkExperienceDTO> findAll() {
        return new ArrayList<>();
    }

}
