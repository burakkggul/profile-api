package tr.com.burakgul.profileapi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserRequest {
    private String username;

    private String firstname;

    private String lastname;

    private String email;

    private String password;
}
