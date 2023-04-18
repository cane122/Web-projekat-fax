package goodReadClone.WebProject.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    private boolean primary;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ShelfInstance shelfInstance;

}
