package tr.com.burakgul.profileapi.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.HeaderRequest;
import tr.com.burakgul.profileapi.model.dto.HeaderResponse;
import tr.com.burakgul.profileapi.service.main.HeaderService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/header")
public class HeaderController {

    private final HeaderService headerService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody HeaderRequest headerRequest){
        return ResponseEntity.ok(this.headerService.save(headerRequest));
    }

    @PutMapping
    public ResponseEntity<HeaderResponse> update(@RequestBody HeaderRequest headerRequest){
        return ResponseEntity.ok(this.headerService.update(headerRequest));
    }

    @GetMapping
    public ResponseEntity<Object> findHeader(){
        return ResponseEntity.ok(this.headerService.findHeader());
    }
}
