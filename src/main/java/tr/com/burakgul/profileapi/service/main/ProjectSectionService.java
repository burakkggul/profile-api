package tr.com.burakgul.profileapi.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
import tr.com.burakgul.profileapi.core.util.ObjectUpdaterUtil;
import tr.com.burakgul.profileapi.model.dto.main.ProjectDTO;
import tr.com.burakgul.profileapi.model.dto.main.ProjectSectionDTO;
import tr.com.burakgul.profileapi.model.entity.main.Project;
import tr.com.burakgul.profileapi.model.entity.main.ProjectSection;
import tr.com.burakgul.profileapi.repository.main.ProjectSectionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectSectionService {

    private final ProjectSectionRepository projectSectionRepository;
    private final ProjectService projectService;
    private final DTOMapper dtoMapper;

    @Transactional(readOnly = true)
    public ProjectSectionDTO findProjectSection() {
        ProjectSection currentProjectSection = this.projectSectionRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Section bulunamadi."));
        return this.dtoMapper.mapModel(currentProjectSection, ProjectSectionDTO.class);
    }

    @Transactional
    public ProjectSectionDTO save(ProjectSectionDTO projectSectionRequest) {
        ProjectSection projectSectionToBeSavedOrUpdated = this.dtoMapper.mapModel(projectSectionRequest, ProjectSection.class);
        this.setProjectSectionWithRelations(projectSectionToBeSavedOrUpdated, projectSectionRequest);
        ProjectSection savedProjectSection = this.projectSectionRepository.save(projectSectionToBeSavedOrUpdated);
        return this.dtoMapper.mapModel(savedProjectSection, ProjectSectionDTO.class);
    }

    @Transactional
    public ProjectSectionDTO update(ProjectSectionDTO projectSectionRequest) {
        Optional<ProjectSection> projectSectionOptional = this.projectSectionRepository.findTopByOrderByIdDesc();
        if (projectSectionOptional.isPresent()) {
            ProjectSection projectSectionToBeSavedOrUpdated = projectSectionOptional.get();
            this.updateProjectSectionWithRelations(projectSectionToBeSavedOrUpdated, projectSectionRequest);
            ProjectSection savedProjectSection = this.projectSectionRepository.save(projectSectionToBeSavedOrUpdated);
            return this.dtoMapper.mapModel(savedProjectSection, ProjectSectionDTO.class);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProjectSection bulunamadi!");
        }
    }

    private void updateProjectSectionWithRelations(ProjectSection projectSectionToBeSavedOrUpdated, ProjectSectionDTO projectSectionRequest) {
        ProjectSection upToDateProjectSection = this.dtoMapper.mapModel(projectSectionRequest, ProjectSection.class);
        ObjectUpdaterUtil.updateObject(projectSectionToBeSavedOrUpdated, upToDateProjectSection, Arrays.asList("id", "projects"));
        this.setProjectSectionWithRelations(projectSectionToBeSavedOrUpdated, projectSectionRequest);
    }

    private void setProjectSectionWithRelations(ProjectSection projectSectionToBeSavedOrUpdated, ProjectSectionDTO projectSectionRequest){
        List<ProjectDTO> savedProjects = this.projectService.saveAll(projectSectionRequest.getProjects());
        List<Project> mappedProjectsToSetProjectSection = this.dtoMapper.mapListModel(savedProjects, Project.class);
        projectSectionToBeSavedOrUpdated.setProjects(mappedProjectsToSetProjectSection);
    }
}
