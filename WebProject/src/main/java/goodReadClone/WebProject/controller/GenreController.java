package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Genre;
import goodReadClone.WebProject.service.GenreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/api/genres")
    public List<Genre> getGenres(){
        return genreService.findAll();
    }

    @PostMapping("/api/addGenre/{genreName}")
    public ResponseEntity<String> addGenre(@PathVariable String genreName, HttpSession session){
        String userRole = (String) session.getAttribute("user_type");
        if(!userRole.equals("Admin")){
            return new ResponseEntity<>("You don't have admin privilages", HttpStatus.UNAUTHORIZED);
        }
        genreService.saveByName(genreName);
        return new ResponseEntity<>("Genre added", HttpStatus.OK);
    }
}
