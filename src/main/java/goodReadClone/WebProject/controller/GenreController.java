package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Genre;
import goodReadClone.WebProject.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/api/genres")
    public List<Genre> getGenres(){
        List<Genre> genreList = genreService.findAll();
        return genreList;

    }
}
