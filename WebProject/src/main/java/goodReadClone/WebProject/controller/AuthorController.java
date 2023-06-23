package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.service.AuthorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AuthorController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private AuthorService authorService;

    @PutMapping("/author/activate")
    public ResponseEntity activateAuthor(@RequestBody Request zahtev){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
