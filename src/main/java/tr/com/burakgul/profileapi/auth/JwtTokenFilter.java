package tr.com.burakgul.profileapi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tr.com.burakgul.profileapi.service.UserDetailService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenCore = request.getHeader("Authorization");
        String token = null;
        String username = null;

        /**
         * Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
         * .
         * eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ
         * .
         * SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
         */
        if(tokenCore != null && tokenCore.contains("Bearer") && tokenCore.split(" ").length > 1){
            token = tokenCore.split(" ")[1];
            username = this.tokenManager.getUserFromToken(token);
        }

        /**
         * SecurityContextHolder.getContext().getAuthentication() metodundan dönen değer authenticate olmuş bir
         * user olması durumunda dolu dönecektir. Biz burada kimsenin auth olmamış olmasını istiyoruz.
         */
        if(token != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            if(this.tokenManager.hasTokenValid(token)){
                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
                if(Objects.nonNull(userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request,response);
    }
}
