package tr.com.burakgul.profileapi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tr.com.burakgul.profileapi.model.entity.User;
import tr.com.burakgul.profileapi.repository.blog.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return new UserPrincipal(optionalUser.get());
        }else {
            throw new UsernameNotFoundException(username + " is not found.");
        }
    }
}
