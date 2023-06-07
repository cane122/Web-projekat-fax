package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.RequestDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.service.AuthorService;
import goodReadClone.WebProject.service.RequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class RequestController {
    @Autowired
    AuthorService authorService;
    @Autowired
    RequestService requestService;
    @PostMapping("/postRequest/{author_id}")
    public ResponseEntity requestAdd(@PathVariable Long admin_id, RequestDTO requestDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Author> mirko = authorService.findById(admin_id);
        if(mirko.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requestService.addRequest(requestDTO, mirko.get());
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/get/requests")
    public List<Request> getRequestsWaiting(HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != "Admin"){
            return null;
        }
        List<Request> requests = requestService.getRequests();
        for (Request r: requests) {
            if(!r.getStatusString().equals("WAITING")){
                requests.remove(r);
            }
        }
        return requests;
    }
    @PatchMapping("/accrequest/{author_id}")
    public ResponseEntity requestAccept(@PathVariable Long admin_id, RequestDTO requestDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Author> mirko = authorService.findById(admin_id);
        if(mirko.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requestService.addRequest(requestDTO, mirko.get());
        return new ResponseEntity(HttpStatus.OK);
    }
    @PatchMapping("/denyrequest/{author_id}")
    public ResponseEntity requestDeny(@PathVariable Long admin_id, RequestDTO requestDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Author> mirko = authorService.findById(admin_id);
        if(mirko.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requestService.addRequest(requestDTO, mirko.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
