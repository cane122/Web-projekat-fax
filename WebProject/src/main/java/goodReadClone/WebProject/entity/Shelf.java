package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
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
