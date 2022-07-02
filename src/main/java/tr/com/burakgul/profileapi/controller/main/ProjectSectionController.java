package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.main.ProjectSectionDTO;
import tr.com.burakgul.profileapi.service.main.ProjectSectionService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projectSection")
public class ProjectSectionController {

    private final ProjectSectionService projectSectionService;

    @GetMapping
    public ResponseEntity<ProjectSectionDTO> find(){
        return ResponseEntity.ok(this.projectSectionService.find());
    }

    @PostMapping
    public ResponseEntity<ProjectSectionDTO> save(@RequestBody ProjectSectionDTO projectSectionDTO){
        return ResponseEntity.created(URI.create("/projectSection"))
                .body(this.projectSectionService.save(projectSectionDTO));
    }

    @PutMapping
    public ResponseEntity<ProjectSectionDTO> update(@RequestBody ProjectSectionDTO projectSectionDTO){
        return ResponseEntity.ok(this.projectSectionService.update(projectSectionDTO));
    }
}
