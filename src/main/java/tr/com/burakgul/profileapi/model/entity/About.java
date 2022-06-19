package tr.com.burakgul.profileapi.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "about")
@Getter
@Setter
@NoArgsConstructor
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "header")
    private String header;

    @Column(name = "content")
    private String content;

    @OneToMany
    @JoinColumn(name = "about_id")
    private List<Contact> contacts;
}
