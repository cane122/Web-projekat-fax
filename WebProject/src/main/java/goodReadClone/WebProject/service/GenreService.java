package goodReadClone.WebProject.service;

import goodReadClone.WebProject.entity.Genre;
import goodReadClone.WebProject.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public Genre findByName(String name){
        Optional<Genre> found = genreRepository.findByName(name);
        return found.orElse(null);
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    public void save(Genre genre){
        genreRepository.save(genre);
    }

    public void saveByName(String genreName) {
        genreRepository.save(new Genre(genreName));
    }
}
