package tr.com.burakgul.profileapi.model.entity.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import tr.com.burakgul.profileapi.core.util.DeletedDateUtil;
import tr.com.burakgul.profileapi.model.entity.base.HistoricalBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "DELETED_DATE='1970-01-01 00:00:00.000'")
public class Category extends HistoricalBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted_date")
    private Date deletedDate = DeletedDateUtil.getDefaultDeletedDate();
}
