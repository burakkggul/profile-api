package tr.com.burakgul.profileapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burakgul.profileapi.model.dto.UserRequest;
import tr.com.burakgul.profileapi.model.dto.UserResponse;
import tr.com.burakgul.profileapi.model.dto.LoginDTO;
import tr.com.burakgul.profileapi.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok().headers(this.authService.login(loginDTO)).body("Login success.");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.authService.signup(userRequest));
    }
}
