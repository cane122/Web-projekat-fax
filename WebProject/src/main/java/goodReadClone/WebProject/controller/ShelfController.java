package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.service.BookService;
import goodReadClone.WebProject.service.ShelfInstanceService;
import goodReadClone.WebProject.service.ShelfService;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    @Autowired
    private ShelfService shelfService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ShelfInstanceService shelfInstanceService;

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
    @PutMapping("/{id_shelf}/putBook/{id_book}")
    public ResponseEntity<String> putBookOnShelf(@PathVariable("id_shelf") Long id_shelf,@PathVariable("id_book") Long id_book, HttpSession session){
        User user = (User) session.getAttribute("user");
        Shelf shelf = user.getShelfById(id_shelf);
        Optional<Book> book = bookService.findById(id_book);
        if(shelf == null || book.isEmpty()){
            return new ResponseEntity<>("Ne postoji polica ili knjiga", HttpStatus.BAD_REQUEST);
        }
        int bookOnPrimary = 0;
        for(Shelf s: user.getShelfs()){
            for(ShelfInstance si: s.getShelfInstance()){
                Book knjiga =si.getBook();
                if(knjiga.getId() == id_book){
                    if(s.isPrimary()){
                        bookOnPrimary += 1;
                    }

                }
            }
        }
        if(bookOnPrimary == 1 && shelf.isPrimary() == true){
            return new ResponseEntity<>("Moze max na jedan primarni da se stavi",HttpStatus.BAD_REQUEST);
        }
        if(bookOnPrimary == 0 && shelf.isPrimary() != true){
            return new ResponseEntity<>("Ne moze jer nema je na primarnom",HttpStatus.BAD_REQUEST);
        }
        shelf.getShelfInstance().add(new ShelfInstance(book.get()));
        shelfService.save(shelf);
        return new ResponseEntity<>("Uspesno dodata knjiga na policu",HttpStatus.OK);
    }
    @DeleteMapping("/{id_shelf}/deleteBook/{id_book}")
    public ResponseEntity<String> deleteBookOnShelf(@PathVariable("id_shelf") Long id_shelf,@PathVariable("id_book") Long id_book, HttpSession session){
        User user = (User) session.getAttribute("user");
        Shelf shelf = user.getShelfById(id_shelf);
        Optional<Book> book = bookService.findById(id_book);
        if(shelf == null || book.isEmpty()){
            return new ResponseEntity<>("Ne postoji polica ili knjiga", HttpStatus.BAD_REQUEST);
        }
        int bookOnPrimary = 0;
        for(Shelf s: user.getShelfs()){
            for(ShelfInstance si: s.getShelfInstance()){
                Book knjiga =si.getBook();
                if(knjiga.getId() == id_book){
                    if(s.isPrimary()){
                        bookOnPrimary +=1;
                    }

                }
            }
        }
        if(bookOnPrimary == 1 && shelf.isPrimary() == true){
            for(Shelf s: user.getShelfs()){
                for(ShelfInstance si: s.getShelfInstance()){
                    Book knjiga =si.getBook();
                    if(knjiga.getId() == id_book){
                        s.delete(si);
                        shelfService.save(s);
                        shelfInstanceService.delete(si);
                    }
                }
            }
            return new ResponseEntity<>("Uspesno izbrisana knjiga iz police",HttpStatus.OK);

        }
        shelf.delete();
        shelfService.save(shelf);
        return new ResponseEntity<>("Uspesno izbrisana knjiga iz police",HttpStatus.OK);
    }

}
