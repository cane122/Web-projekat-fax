package goodReadClone.WebProject.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Shelf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column(name = "is_primary")
    private boolean primary;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ShelfInstance> shelfInstance = new HashSet<>();

    public Shelf(String name, boolean primary) {
        this.name = name;
        this.primary = primary;
    }
    public Shelf (){
    }

    public void delete(ShelfInstance si) {
        shelfInstance.remove(si);
    }
}
