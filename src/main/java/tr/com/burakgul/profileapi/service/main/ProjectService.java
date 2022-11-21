package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.model.dto.main.ProjectDTO;
import tr.com.burakgul.profileapi.model.entity.main.Project;
import tr.com.burakgul.profileapi.repository.main.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DTOMapper dtoMapper;

    @Transactional
    public List<ProjectDTO> saveAll(List<ProjectDTO> projectsRequest){
        List<Project> upToDateProjects = this.dtoMapper.mapListModel(projectsRequest, Project.class);
        List<Project> savedProjects = this.projectRepository.saveAll(upToDateProjects);
        return this.dtoMapper.mapListModel(savedProjects, ProjectDTO.class);
    }
}
//TODO findAll, save ve update methodlari eklenebilir hatta delete methodu

