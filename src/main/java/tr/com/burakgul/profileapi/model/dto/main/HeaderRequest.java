package tr.com.burakgul.profileapi.model.dto.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.dto.ImageDTO;

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
