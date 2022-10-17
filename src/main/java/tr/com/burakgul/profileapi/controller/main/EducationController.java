package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.burakgul.profileapi.model.dto.main.EducationDTO;
import tr.com.burakgul.profileapi.model.entity.main.Education;
import tr.com.burakgul.profileapi.service.main.EducationService;

import java.util.List;

@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {
    private final EducationService educationService;

    @GetMapping
    public ResponseEntity<List<EducationDTO>> findAll() {
        return ResponseEntity.ok(this.educationService.findAll());
    }

    @PostMapping
    public ResponseEntity<EducationDTO> save(@RequestBody EducationDTO educationRequest) {
        return ResponseEntity.ok(this.educationService.save(educationRequest));
    }

    @PutMapping("/{educationId}")
    public ResponseEntity<EducationDTO> update(@RequestBody EducationDTO educationRequest, @PathVariable Long educationId) {
        return ResponseEntity.ok(this.educationService.update(educationRequest, educationId));
    }

}
