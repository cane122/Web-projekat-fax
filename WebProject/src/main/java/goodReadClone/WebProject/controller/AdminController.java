package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.AuthorDTO;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/createauthor")
    public ResponseEntity<String> createAuthor(@RequestBody AuthorDTO authorDTO, HttpSession session){
        String userRole = (String) session.getAttribute("user_role");
        if(userRole.equals("Admin")){
            return new ResponseEntity<>("You don't have admin privilages", HttpStatus.UNAUTHORIZED);
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
}
