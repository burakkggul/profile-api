package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.burakgul.profileapi.model.dto.main.SkillDTO;
import tr.com.burakgul.profileapi.service.main.SkillService;

import java.util.List;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;


    @GetMapping
    public ResponseEntity<List<SkillDTO>> findAll() {
        return ResponseEntity.ok(this.skillService.findAll());
    }

    @PostMapping
    public ResponseEntity<SkillDTO> save(@RequestBody SkillDTO skillRequest) {
        return ResponseEntity.ok(this.skillService.save(skillRequest));
    }

    @PutMapping("/skillId")
    public ResponseEntity<SkillDTO> update(@RequestBody SkillDTO skillRequest, @PathVariable Long skillId) {
        return ResponseEntity.ok(this.skillService.update(skillRequest, skillId));
    }
}
