package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import tr.com.burakgul.profileapi.auth.TokenManager;
import tr.com.burakgul.profileapi.model.dto.LoginDTO;
import tr.com.burakgul.profileapi.model.dto.UserRequest;
import tr.com.burakgul.profileapi.model.dto.UserResponse;
import tr.com.burakgul.profileapi.model.entity.User;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;

    private final TokenManager tokenManager;

    private final UserDetailService userDetailService;

    public HttpHeaders login(LoginDTO loginDTO) {

    }

    public UserResponse signup(UserRequest userRequest) {
    }

    public HttpHeaders login1(LoginDTO loginDTO) {
    }

    public UserResponse signup1(UserRequest userRequest) {
    }

    public HttpHeaders login2(LoginDTO loginDTO) {
    }

    public UserResponse signup2(UserRequest userRequest) {
    }
}
