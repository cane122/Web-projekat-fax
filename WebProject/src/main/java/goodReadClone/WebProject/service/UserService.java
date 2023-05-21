package goodReadClone.WebProject.service;

import goodReadClone.WebProject.DTO.LoginDTO;
import goodReadClone.WebProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import goodReadClone.WebProject.entity.User;

import java.util.List;
import java.util.Optional;

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

    public User save(User user){
        return userRepository.save(user);
    }
}
