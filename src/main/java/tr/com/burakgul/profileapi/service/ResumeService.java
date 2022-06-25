package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.ResumeDTO;
import tr.com.burakgul.profileapi.model.dto.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.WorkExperience;
import tr.com.burakgul.profileapi.repository.ResumeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeDTO save(ResumeDTO resume){
        return resume;
    }

    public ResumeDTO update(ResumeDTO resume){
        return resume;
    }

    public List<ResumeDTO> findAll(){
        return new ArrayList<>();
    }
}
