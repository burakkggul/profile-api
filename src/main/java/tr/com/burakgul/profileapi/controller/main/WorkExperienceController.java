package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.main.WorkExperienceDTO;
import tr.com.burakgul.profileapi.model.entity.main.WorkExperience;
import tr.com.burakgul.profileapi.service.main.WorkExperienceService;

@RestController
@RequestMapping("/workExperience")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<WorkExperienceDTO> save(@RequestBody WorkExperienceDTO workExperienceRequest) {
        return ResponseEntity.ok(this.workExperienceService.save(workExperienceRequest));
    }

    @PutMapping("/workExperienceId")
    public ResponseEntity<WorkExperienceDTO> update(@RequestBody WorkExperienceDTO workExperienceRequest, @PathVariable Long workExperienceId){
        return ResponseEntity.ok(this.workExperienceService.update(workExperienceRequest, workExperienceId));
    }
}
