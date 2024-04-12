package sorenrahimi.g4s2m2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String dateOfBirth;
    private String avatar;

    /*@OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @JsonIgnore
    List<BlogPost> blogPostList;*/
}
