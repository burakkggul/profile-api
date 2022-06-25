package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.EducationDTO;
import tr.com.burakgul.profileapi.repository.EducationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    public EducationDTO save(EducationDTO education){
        return education;
    }

    public EducationDTO update(EducationDTO education){
        return education;
    }

    public List<EducationDTO> findAll(){
        return new ArrayList<>();
    }
}
