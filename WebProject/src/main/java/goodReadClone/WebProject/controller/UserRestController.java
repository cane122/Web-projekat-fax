package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.UserDTO;
import goodReadClone.WebProject.entity.Reader;
import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.BookService;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }


    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @GetMapping("/api/users/{id}/shelfs/")
    public Set<Shelf> getShelvesFromUser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user.getShelfs();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user;
    }

    @PutMapping("/user/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || user_type.equals("Admin")){
            return new ResponseEntity<>("Not Reader", HttpStatus.UNAUTHORIZED);
        }
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        if(userDTO.getEmail() != isLogged.getEmail() || userDTO.getPassword() != isLogged.getPassword()){
            if(userDTO.getConformationEmail() == null || userDTO.getConformationPassword()== null){
                return new ResponseEntity<>("Morate da unesete conformation email i password", HttpStatus.UNAUTHORIZED);
            }
            if(!userDTO.getConformationPassword().equals(isLogged.getPassword()) || !userDTO.getConformationEmail().equals(isLogged.getEmail())){
                return new ResponseEntity<>("Uneta sifra nije odgovarajuca", HttpStatus.UNAUTHORIZED);
            }
        }
        // create user object
        Reader user = new Reader();
        user.setId(isLogged.getId());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDateBirth(userDTO.getDateBirth());
        user.setDescription(userDTO.getDescription());
        user.setImage(userDTO.getImage());

        userService.save(user);
        session.setAttribute("user", user);
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }

}