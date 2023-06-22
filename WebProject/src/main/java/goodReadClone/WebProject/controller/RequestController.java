package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.RequestDTO;
import goodReadClone.WebProject.entity.Author;
import goodReadClone.WebProject.entity.Request;
import goodReadClone.WebProject.service.AuthorService;
import goodReadClone.WebProject.service.MailService;
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
@CrossOrigin
public class RequestController {
    @Autowired
    AuthorService authorService;
    @Autowired
    RequestService requestService;
    @Autowired
    MailService mailService;
    @PostMapping("/postRequest/{author_id}")
    public ResponseEntity requestAdd(@PathVariable Long author_id, @RequestBody RequestDTO requestDTO, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Author> mirko = authorService.findById(author_id);
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
            if(r.getStatus() == null){
                continue;
            }
            if(!r.getStatusString().equals("WAITING")){
                requests.remove(r);
            }
        }
        return requests;
    }
    //TODO ISPROBAJ SVE OVO I NAMESTI DA TI MEJL RADI
    @PatchMapping("/accrequest/{request_id}")
    public ResponseEntity requestAccept(@PathVariable Long request_id, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != "Admin"){
            return null;
        }
        Optional<Request> request = requestService.getRequestsByID(request_id);
        if(request.isEmpty()){
            return null;
        }
        Optional<Author> authorOptional = authorService.getById(request.get().getUser().getId());
        if(authorOptional.isEmpty()){
            return null;
        }
        String text = new String("Cestitam upravo ste dobili nalog autora\n Email:" + authorOptional.get().getEmail()+ "\tSifra:" + authorOptional.get().getPassword());
        mailService.sendMail(text,request.get().getEmail());
        authorService.authorTurnONById(request.get().getUser().getId());
        requestService.updateRequest(request.get());
        return new ResponseEntity(HttpStatus.OK);
    }
    @PatchMapping("/denyrequest/{request_id}")
    public ResponseEntity requestDeny(@PathVariable Long request_id, HttpSession session){
        String user_type = (String) session.getAttribute("user_type");
        if(user_type != "Admin"){
            return null;
        }
        Optional<Request> request = requestService.getRequestsByID(request_id);
        if(request.isEmpty()){
            return null;
        }
        Optional<Author> authorOptional = authorService.getById(request.get().getUser().getId());
        if(authorOptional.isEmpty()){
            return null;
        }
        String text = new String("Zao mi je ali niste izabrani kao autor, vise srece drugi put");
        mailService.sendMail(text,request.get().getEmail());
        requestService.updateRequestDeny(request.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
