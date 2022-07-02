package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
import tr.com.burakgul.profileapi.repository.main.EducationRepository;

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
