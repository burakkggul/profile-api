package tr.com.burakgul.profileapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactRequest {
    private Long id;
    private String fullName;
    private String email;
    private String website;
}
