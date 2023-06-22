package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.ShelfDTO;
import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.ReaderService;
import goodReadClone.WebProject.service.ShelfService;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@Transactional
@RestController
@RequestMapping("/reader")
@CrossOrigin
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private UserService userService;

    @DeleteMapping("/shelves/remove/{id}")
    public ResponseEntity<String> deleteShelf(@PathVariable("id") Long id, HttpSession session) {
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Nisi citalac", HttpStatus.UNAUTHORIZED);
        }
        User user = (User) session.getAttribute("user");
        if(user.getShelfById(id) == null){
            return new ResponseEntity<>("No shelf by id", HttpStatus.BAD_REQUEST);
        }
        if(user.getShelfById(id).isPrimary() == true){
            return new ResponseEntity<>("No way hose, that Shelf is primary",HttpStatus.BAD_REQUEST);
        }
        try {
            user.removeShelf(user.getShelfById(id));
            userService.save(user);
            session.setAttribute("user",user);
            shelfService.deleteShelf(id);
            return new ResponseEntity<>("Shelf deleted successfully", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("Shelf not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while deleting the shelf", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("shelves/add")
    public ResponseEntity<Shelf> createShelf(@RequestBody ShelfDTO shelf, HttpSession session) {
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(shelfService.findByName(shelf.getName()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = (User) session.getAttribute("user");
        try {
            Shelf createdShelf = shelfService.createShelf(shelf);
            user.addShelf(createdShelf);
            session.setAttribute("user", user);
            userService.save(user);
            return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("shelves/edit/{shelf_id}")
    public ResponseEntity<Shelf> editShelf (@RequestBody ShelfDTO shelf, @PathVariable Long shelf_id, HttpSession session) {
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(shelfService.findById(shelf_id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = (User) session.getAttribute("user");
        try {
            Shelf createdShelf = shelfService.findById(shelf_id).get();
            if(createdShelf.isPrimary()){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            createdShelf.setId(shelf_id);
            createdShelf.setName(shelf.getName());
            createdShelf.setShelfInstance(user.getShelfById(shelf_id).getShelfInstance());
            createdShelf.setPrimary(user.getShelfById(shelf_id).isPrimary());
            user.editShelf(createdShelf);
            session.setAttribute("user", user);
            shelfService.save(createdShelf);
            return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
