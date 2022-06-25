package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.WorkExperience;
import tr.com.burakgul.profileapi.repository.WorkExperienceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;

    public WorkExperienceDTO save(WorkExperienceDTO workExperience){
        return workExperience;
    }

    public WorkExperienceDTO update(WorkExperienceDTO workExperience){
        return workExperience;
    }

    public List<WorkExperienceDTO> findAll(){
        return new ArrayList<>();
    }

}
