package goodReadClone.WebProject.service;

import goodReadClone.WebProject.DTO.AuthorDTO;
import goodReadClone.WebProject.DTO.LoginDTO;
import goodReadClone.WebProject.entity.*;
import goodReadClone.WebProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findOne(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);

    }

    public boolean doesUserExist(String usernameOrEmail){
        if(userRepository.existsByEmail(usernameOrEmail) ) {
            return true;
        }
        if(userRepository.existsByUsername(usernameOrEmail)){
            return true;
        }
        return false;
    }

    public User login(LoginDTO login){
        Optional<User> user = userRepository.findByUsernameOrEmail(login.getUsernameOrEmail(),login.getUsernameOrEmail());
        if(user.isEmpty()){
            return null;
        }
        if(user.get().getPassword().equals(login.getPassword())){
            return user.get();
        }
        return null;
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void createAuthor(AuthorDTO authorDTO) {
        userRepository.save(new Author(authorDTO.getName(), authorDTO.getLastname(), authorDTO.getUsername(), authorDTO.getEmail(), authorDTO.getPassword(), authorDTO.getDateBirth(), authorDTO.getImage(), authorDTO.getDescription()));
    }

    public String getUsersRole(User loggedUser) {
        if (loggedUser.getClass().equals(Admin.class)) {
            return "Admin";
        } else if (loggedUser.getClass().equals(Reader.class)) {
            return "Reader";
        } else if (loggedUser.getClass().equals(Author.class)) {
            return "Author";
        }
        return "User";
    }
    public void delteShelf(User user){
    }
    public Set<Shelf> getShelves(User user) {
        return user.getShelfs();
    }

}
