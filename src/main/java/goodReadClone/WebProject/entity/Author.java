package goodReadClone.WebProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "author")
public class Author extends User {
    @Column
    private boolean active;

    @ManyToMany(mappedBy = "listAuthors")
    private Set<Book> listBooks = new HashSet<Book>();

}
