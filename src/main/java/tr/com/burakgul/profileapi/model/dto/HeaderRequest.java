package tr.com.burakgul.profileapi.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HeaderRequest {
    private String title;
    private String subtitle;
    private List<SocialMediaDTO> socialMedia;
    private ImageDTO image;
}
