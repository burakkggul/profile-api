package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.burakgul.profileapi.model.dto.main.SkillSectionDTO;
import tr.com.burakgul.profileapi.service.main.SkillSectionService;

@RestController
@RequestMapping("/skillSection")
@RequiredArgsConstructor

public class SkillSectionController {
    public final SkillSectionService skillSectionService;

    @GetMapping
    public ResponseEntity<SkillSectionDTO> findSkillSection() {
        return ResponseEntity.ok(this.skillSectionService.findSkillSection());
    }

    @PostMapping
    public ResponseEntity<SkillSectionDTO> save(@RequestBody SkillSectionDTO skillSectionRequest) {
        return ResponseEntity.ok(this.skillSectionService.save(skillSectionRequest));
    }

    @PutMapping
    public ResponseEntity<SkillSectionDTO> update(@RequestBody SkillSectionDTO skillSectionRequest) {
        return ResponseEntity.ok(this.skillSectionService.update(skillSectionRequest));
    }

}
