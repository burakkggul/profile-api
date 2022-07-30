package tr.com.burakgul.profileapi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends UsernamePasswordAuthenticationFilter {
    private final TokenManager tokenManager;
    private final UserDetailService userDetailService;

}
