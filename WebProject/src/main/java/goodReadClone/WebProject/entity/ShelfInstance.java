package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class ShelfInstance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Review review;

    @ManyToOne
    private Book book;

    public ShelfInstance(Book book) {
        this.book = book;
    }
    public ShelfInstance() {
    }
}
