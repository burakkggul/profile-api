package tr.com.burakgul.profileapi.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tr.com.burakgul.profileapi.auth.TokenManager;
import tr.com.burakgul.profileapi.core.helper.DTOMapper;
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

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final DTOMapper dtoMapper;

    public HttpHeaders login(LoginDTO loginDTO) {
        if (loginDTO != null) {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + this.tokenManager.generateToken(loginDTO.getUsername()));
            return headers;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body not found.");
        }
    }

    public UserResponse signup(UserRequest userRequest) {
        try {
            User user = this.dtoMapper.mapModel(userRequest, User.class);
            user.setPassword(this.bCryptPasswordEncoder.encode(userRequest.getPassword()));
            return this.userDetailService.save(user);
        } catch (Exception e) {
            this.LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }
}
