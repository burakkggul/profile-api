package tr.com.burakgul.profileapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Category extends HistoricalBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
