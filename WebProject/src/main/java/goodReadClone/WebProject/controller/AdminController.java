package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.AdminDTO;
import goodReadClone.WebProject.DTO.AuthorDTO;
import goodReadClone.WebProject.DTO.UserDTO;
import goodReadClone.WebProject.entity.Reader;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

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
    //TODO UPDATE PROFILE AUTHOR zavrsi
    @PutMapping("/author/admin/update")
    public ResponseEntity authorUpdate(@RequestBody AdminDTO adminDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type == null || !user_type.equals("Admin")){
            return new ResponseEntity<>("Not Admin", HttpStatus.UNAUTHORIZED);
        }
        User isLogged = (User) session.getAttribute("user");
        if(isLogged == null){
            return new ResponseEntity<>("User not logged in", HttpStatus.BAD_REQUEST);
        }
        if(adminDTO.isActive()) {
            return new ResponseEntity<>("Admin je vec aktiviran", HttpStatus.UNAUTHORIZED);
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
