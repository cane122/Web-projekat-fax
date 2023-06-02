package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.entity.Shelf;
import goodReadClone.WebProject.entity.User;
import goodReadClone.WebProject.service.BookService;
import goodReadClone.WebProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }


    @GetMapping("/api/users")
    public List<User> getUsers(){
        List<User> userList = userService.findAll();
        return userList;
    }

    @GetMapping("/api/users/{id}/shelfs/")
    public Set<Shelf> getShelfs(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user.getShelfs();
    }

    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable(name = "id") Long id){
        User user = userService.findOne(id);
        return user;
    }

}
