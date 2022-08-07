package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.repository.main.ProjectRepository;

//TODO
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
}
