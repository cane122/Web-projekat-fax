package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Genre;
import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.ShelfService;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    @Autowired
    private ShelfService shelfService;
    @Autowired
    private UserService userService;

    @PutMapping("/api/addBook")
    public Boolean addBook(){
        //TO-DO implement adding a book to the specific shelf instance
        return false;
    }

    @GetMapping()
    public List<Shelf> getShelves(){
        return shelfService.findAll();
    }
    @GetMapping("/user")
    public Set<Shelf> getShelvesUser(HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return null;
        }
        return userService.getShelves(user);
    }


}
