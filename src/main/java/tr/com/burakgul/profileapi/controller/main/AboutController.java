package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.main.AboutRequest;
import tr.com.burakgul.profileapi.model.dto.main.AboutResponse;
import tr.com.burakgul.profileapi.service.main.AboutService;

@RestController
@RequestMapping("/about")
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @GetMapping
    public ResponseEntity<AboutResponse> findAbout(){
        return ResponseEntity.ok(this.aboutService.findAbout());
    }

    @PostMapping
    public ResponseEntity<AboutResponse> save(@RequestBody AboutRequest aboutRequest){
        return ResponseEntity.ok(this.aboutService.save(aboutRequest));
    }

    @PutMapping
    public ResponseEntity<AboutResponse> update(@RequestBody AboutRequest aboutRequest){
        return ResponseEntity.ok(this.aboutService.update(aboutRequest));
    }
}
