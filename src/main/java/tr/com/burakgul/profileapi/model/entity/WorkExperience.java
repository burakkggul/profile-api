package tr.com.burakgul.profileapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.entity.base.BaseResumeElement;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "work_experience")
@Getter
@Setter
@NoArgsConstructor
public class WorkExperience extends BaseResumeElement {
}
