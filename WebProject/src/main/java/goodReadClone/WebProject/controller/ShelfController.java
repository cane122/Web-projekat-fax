package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShelfController {

    @Autowired
    private ShelfService shelfService;

    @PutMapping("/api/addBook")
    public Boolean addBook(){
        //TO-DO implement adding a book to the specific shelf instance
        return false;
    }
}
