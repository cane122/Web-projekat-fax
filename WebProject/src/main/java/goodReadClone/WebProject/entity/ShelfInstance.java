package goodReadClone.WebProject.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ShelfInstance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Review review;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Book book;
}
