package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.ResumeDTO;
import tr.com.burakgul.profileapi.repository.main.ResumeRepository;

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

    public ResumeDTO find(){
        return new ResumeDTO();
    }
}
