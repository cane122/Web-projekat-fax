package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.AdminDTO;
import goodReadClone.WebProject.DTO.AuthorDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.AuthorService;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorService authorService;

    @PostMapping("/admin/createauthor")
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO, HttpSession session){
        String userRole = (String) session.getAttribute("user_type");
        if(!userRole.equals("Admin")){
            return new ResponseEntity<>("You don't have admin privileges", HttpStatus.UNAUTHORIZED);
        }
        if(authorDTO.getName().isEmpty() || authorDTO.getPassword().isEmpty()){
            return new ResponseEntity<>("Invalid author data", HttpStatus.BAD_REQUEST);
        }
        if(userService.doesUserExist(authorDTO.getUsername())){
            return new ResponseEntity<>("User already exists",HttpStatus.BAD_REQUEST);
        }
        userService.createAuthor(authorDTO);

        return new ResponseEntity<>("Author created successfully!.", HttpStatus.OK);
    }
    @PutMapping("/author/admin/update/{author_id}")
    public ResponseEntity authorUpdate(@PathVariable Long author_id, @RequestBody AdminDTO adminDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || !user_type.equals("Admin")){
            return new ResponseEntity<>("Not Admin", HttpStatus.UNAUTHORIZED);
        }
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        // create user object
        Optional<Author> author = authorService.getById(author_id);
        if(author.get() == null){
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }
        Author user = author.get();
        user.setId(author_id);
        user.setName(adminDTO.getName());
        user.setLastname(adminDTO.getLastname());
        user.setUsername(adminDTO.getUsername());
        user.setEmail(adminDTO.getEmail());
        user.setPassword(adminDTO.getPassword());
        user.setDateBirth(adminDTO.getDateBirth());
        user.setDescription(adminDTO.getDescription());
        user.setImage(adminDTO.getImage());

        authorService.save(user);
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }
}
