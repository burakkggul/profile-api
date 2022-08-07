package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.model.dto.main.ProjectSectionDTO;
import tr.com.burakgul.profileapi.repository.main.ProjectSectionRepository;

//TODO
@Service
@RequiredArgsConstructor
public class ProjectSectionService {

    private final ProjectSectionRepository projectSectionRepository;

    public ProjectSectionDTO find() {
        return new ProjectSectionDTO();
    }

    public ProjectSectionDTO save(ProjectSectionDTO projectSectionDTO) {
        return projectSectionDTO;
    }

    public ProjectSectionDTO update(ProjectSectionDTO projectSectionDTO) {
        return projectSectionDTO;
    }
}
