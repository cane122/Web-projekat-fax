package goodReadClone.WebProject.controller;

import goodReadClone.WebProject.DTO.RequestDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @PostMapping("/postRequest/{admin_id}")
    public requestAdd(@PathVariable Long admin_id, RequestDTO requestDTO){

    }
}
