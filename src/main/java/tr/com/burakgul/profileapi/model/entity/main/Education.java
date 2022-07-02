package tr.com.burakgul.profileapi.model.entity.main;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.entity.base.BaseResumeElement;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "education")
@Getter
@Setter
@NoArgsConstructor
public class Education extends BaseResumeElement {
}
