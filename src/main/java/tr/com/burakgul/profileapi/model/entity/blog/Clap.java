package tr.com.burakgul.profileapi.model.entity.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.burakgul.profileapi.model.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "clap",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"post_id", "user_id"}),
                // post id ve user id 1,1 ise bir daha 1,1 gelemez.
                @UniqueConstraint(columnNames = {"comment_id", "user_id"})
                // comment id ve user id 1,1 ise bir daha 1,1 gelemez.
        })
@Getter
@Setter
@NoArgsConstructor
public class Clap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Post post;
}
