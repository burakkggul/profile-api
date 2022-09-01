package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.Education;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.repository.main.EducationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//TODO
@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public List<EducationDTO> findAll() {
        List<Education> educationList = this.educationRepository.findAll();
        if(educationList.size() != 0){
            return this.dtoMapper.mapListModel(educationList, EducationDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Herhangi bir education bulunamadi.");
        }
    }

    @Transactional
    public EducationDTO save(EducationDTO educationRequest) {
        Education upToDateEducation = this.dtoMapper.mapModel(educationRequest, Education.class);
        Education education = this.educationRepository.save(upToDateEducation);
        return this.dtoMapper.mapModel(education, EducationDTO.class);
    }

    @Transactional
    public List<EducationDTO> saveAll(List<EducationDTO> educationRequest) {
        List<Education> upToDateEducations = this.dtoMapper.mapListModel(educationRequest, Education.class);
        List<Education> savedEducations = this.educationRepository.saveAll(upToDateEducations);
        return this.dtoMapper.mapListModel(savedEducations, EducationDTO.class);
    }

    @Transactional
    public EducationDTO update(EducationDTO educationRequest, Long educationId) {
        Optional<Education> optionalEducation = this.educationRepository.findById(educationId);
        if (optionalEducation.isPresent()) {
            Education currentEducation = optionalEducation.get();
            Education upToDateEducation = this.dtoMapper.mapModel(educationRequest, Education.class);
            ObjectUpdaterUtil.updateObject(currentEducation, upToDateEducation, Arrays.asList("id"));
            Education savedEducation = this.educationRepository.save(currentEducation);
            return this.dtoMapper.mapModel(savedEducation, EducationDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bu id ile Education bulunamadi.");
        }
    }
}
