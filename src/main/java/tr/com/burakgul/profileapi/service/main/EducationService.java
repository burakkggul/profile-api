package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
import tr.com.burakgul.profileapi.repository.main.EducationRepository;
import tr.com.burakgul.profileapi.model.entity.main.Education;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final DTOMapper dtoMapper;

    public EducationDTO save(EducationDTO educationRequest){
        Education upToDateEducation = this.dtoMapper.mapModel(educationRequest, Education.class);
        Education education = this.educationRepository.save(upToDateEducation);
        return this.dtoMapper.mapModel(education, EducationDTO.class);
    }

    public List<EducationDTO> saveAll(List<EducationDTO> educationsRequest){
        List<Education> upToDateEducations = this.dtoMapper.mapListModel(educationsRequest, Education.class);
        List<Education> educations = this.educationRepository.saveAll(upToDateEducations);
        return this.dtoMapper.mapListModel(educations, EducationDTO.class);
    }

    public EducationDTO update(EducationDTO education){
        return education;
    }

    public List<EducationDTO> findAll(){
        return new ArrayList<>();
    }
}
