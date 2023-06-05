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

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ShelfInstance> shelfInstance = new HashSet<>();

    public Shelf(String name, boolean primary) {
        this.name = name;
        this.primary = primary;
    }
    public Shelf (){
    }

    public void delete(ShelfInstance si) {
        shelfInstance.remove(si);
        Set<ShelfInstance> newSI = new HashSet<>();
        for(ShelfInstance siTemp: shelfInstance){
            if(!siTemp.getId().equals(si.getId())){
                newSI.add(siTemp);
            }
        }
        shelfInstance = newSI;
    }

    public void deleteInstanceByBookId(Long idBook) {
        Set<ShelfInstance> siSet = new HashSet<>();
        for(ShelfInstance si : shelfInstance){
            if(!si.getBook().getId().equals(idBook)){
                siSet.add(si);
            }
        }
        shelfInstance = siSet;
    }

    public ShelfInstance getShelfInstanceByBookId(Long idBook) {
        for(ShelfInstance si: shelfInstance){
            if(si.getBook().equals(idBook)){
                return si;
            }
        }
        return null;
    }

    public ShelfInstance getShelfInstancefById(Long idShelfInstance) {
        for(ShelfInstance si: shelfInstance){
            if(idShelfInstance.equals(si.getId())){
                return si;
            }
        }
        return null;
    }
    public void editShelfInstance(ShelfInstance shelfInstancePassed){
        for(ShelfInstance si : shelfInstance){
            if(si.getId().equals(shelfInstancePassed.getId())){
                si.setBook(shelfInstancePassed.getBook());
                si.setReview(shelfInstancePassed.getReview());
            }
        }
    }
}
