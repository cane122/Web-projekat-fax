package goodReadClone.WebProject.service;

import goodReadClone.WebProject.DTO.ShelfDTO;
import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf findByName(String name){
        Optional<Shelf> found = shelfRepository.findByName(name);
        return found.orElse(null);
    }


    public void deleteShelf(Long id) {
        shelfRepository.delete(shelfRepository.getReferenceById(id));
    }

    public List<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    public Shelf createShelf(ShelfDTO shelf) {

        Shelf polica = new Shelf(shelf.getName(), false);
        shelfRepository.save(polica);
        return polica;
    }
}
