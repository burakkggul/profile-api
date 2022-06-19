package tr.com.burakgul.profileapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AboutRequest {
    private String pageTitle;
    private String header;
    private String content;
    private List<ContactRequest> contacts;
}
