package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }


    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @GetMapping("/api/users/{id}")
    public User getuser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user;
    }

    @PostMapping("/api/save-user")
    public String saveUser(@RequestBody User user) {
        this.userService.save(user);
        return "Successfully saved an user!";
    }
}