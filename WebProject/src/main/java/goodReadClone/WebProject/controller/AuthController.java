package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.LoginDTO;
import goodReadClone.WebProject.DTO.SignUpDTO;
import goodReadClone.WebProject.entity.Reader;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDto, HttpSession session){
        User isLogged = (User) session.getAttribute("user");
        if(isLogged != null){
            return new ResponseEntity<>("User already logged in", HttpStatus.BAD_REQUEST);
        }
        if(loginDto.getUsernameOrEmail().isEmpty() || loginDto.getPassword().isEmpty())
            return new ResponseEntity<>("Invalid login data", HttpStatus.BAD_REQUEST);

        if(!userService.doesUserExist(loginDto.getUsernameOrEmail())){
            return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
        }
        User loggedUser = userService.login(loginDto);
        if(loggedUser == null){
            return new ResponseEntity<>("Wrong autentication (hacker pls don't hack)",HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute("user", loggedUser);
        session.setAttribute("user_role", userService.getUsersRole(loggedUser));

        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto, HttpSession session){
        User isLogged = (User) session.getAttribute("user");
        if(isLogged != null){
            return new ResponseEntity<>("User already logged in", HttpStatus.BAD_REQUEST);
        }
        // add check for username exists in a DB
        if(userService.doesUserExist(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userService.doesUserExist(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new Reader();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());

        userService.save(user);
        session.setAttribute("user", user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
