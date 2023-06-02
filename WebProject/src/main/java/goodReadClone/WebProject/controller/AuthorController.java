package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PutMapping("/author/activate")
    public ResponseEntity activateAuthor(@RequestBody Request zahtev){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
