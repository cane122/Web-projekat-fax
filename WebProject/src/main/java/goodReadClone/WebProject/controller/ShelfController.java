package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.DTO.ReviewDTO;
import goodReadClone.WebProject.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.*;

@RestController
@RequestMapping("/shelves")
@CrossOrigin
public class ShelfController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private ShelfInstanceService shelfInstanceService;
    @Autowired
    private ReviewService reviewService;

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
        return user.getShelfs();
    }
    @PutMapping("/{id_shelf}/putBook/{id_book}")
    public ResponseEntity<String> putBookOnShelf(@PathVariable("id_shelf") Long id_shelf,@PathVariable("id_book") Long id_book, HttpSession session){
        User user = (User) session.getAttribute("user");
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
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
        ShelfInstance si = new ShelfInstance(book.get());
        shelf.getShelfInstance().add(si);
        Shelf newShelf = shelfService.save(shelf);
        si = shelfInstanceService.save(si);
        return new ResponseEntity<>("Uspesno dodata knjiga na policu",HttpStatus.OK);
    }
    @DeleteMapping("/{id_shelf}/deleteBook/{id_book}")
    public ResponseEntity<String> deleteBookOnShelf(@PathVariable("id_shelf") Long id_shelf,@PathVariable("id_book") Long id_book, HttpSession session){
        User user = (User) session.getAttribute("user");
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
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
                        Long id_toDelete = si.getId();
                        s.delete(si);
                        session.setAttribute("user",user);
                        userService.save(user);
                        shelfService.save(s);
                        shelfInstanceService.delete(si.getId());
                    }
                }
            }
            return new ResponseEntity<>("Uspesno izbrisana knjiga iz police",HttpStatus.OK);
        }
            ShelfInstance obrisi = shelf.getShelfInstanceByBookId(id_book);
            if(obrisi == null){
                return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
            }
            shelf.deleteInstanceByBookId(id_book);
            session.setAttribute("user",user);
            shelfService.save(shelf);

            return new ResponseEntity<>("Uspesno izbrisana knjiga iz police", HttpStatus.OK);
        }

    @PutMapping("/{id_shelf}/addreview/{id_shelfInstance}")
    public ResponseEntity addReview(@PathVariable("id_shelf") Long id_shelf, @PathVariable("id_shelfInstance") Long id_shelfInstance, @RequestBody ReviewDTO review, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Shelf shelf = user.getShelfById(id_shelf);
        ShelfInstance shelfInstance = shelf.getShelfInstancefById(id_shelfInstance);
        Review reviewToBeAdded = new Review(review.getText(), review.getGrade(), review.getReviewDate());
        if (user == null) {
            return new ResponseEntity<>("Nisi prijavljen", HttpStatus.UNAUTHORIZED);
        }
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
        if (shelf == null || review == null) {
            return new ResponseEntity<>("Ne postoji polica ili review", HttpStatus.BAD_REQUEST);
        }
        if (shelfInstance == null) {
            return new ResponseEntity<>("nije nadjen shelf", HttpStatus.BAD_REQUEST);
        }
        shelfInstance.addReview(reviewToBeAdded);
        reviewService.save(reviewToBeAdded);
        session.setAttribute("user", user);
        shelfService.save(shelf);
        bookService.save(shelfInstance.getBook());

        return new ResponseEntity<>("Uspesno dodat review", HttpStatus.OK);
    }

}
