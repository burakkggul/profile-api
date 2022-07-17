package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.main.ResumeDTO;
import tr.com.burakgul.profileapi.service.main.ResumeService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<ResumeDTO> find(){
        return ResponseEntity.ok(this.resumeService.find());
    }

    @PutMapping
    public ResponseEntity<ResumeDTO> update(@RequestBody ResumeDTO resume, @RequestParam Long id){
        return ResponseEntity.ok(this.resumeService.update(resume, id));
    }

    @PostMapping
    public ResponseEntity<ResumeDTO> save(@RequestBody ResumeDTO resumeDTO){
        return ResponseEntity.created(URI.create("/resume"))
                .body(this.resumeService.save(resumeDTO));
    }
}
