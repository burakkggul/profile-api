package tr.com.burakgul.profileapi.model.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BaseResumeElementDTO {

    private Long id;
    private String title;
    private String companyName;
    private Date startDate;
    private Date endDate;
    private String description;
}
