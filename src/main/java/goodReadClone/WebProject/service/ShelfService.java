package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Book;
import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    public Shelf findByName(String name){
        Optional<Shelf> found = shelfRepository.findByTitle(name);
        return found.orElse(null);
    }


}
