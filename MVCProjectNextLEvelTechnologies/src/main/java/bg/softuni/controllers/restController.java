package bg.softuni.controllers;

import bg.softuni.dtos.users.UserShortInfoDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class restController {


    @GetMapping("")
    public String index(){
        return "user/login";
    }
@GetMapping("/dto")
    public UserShortInfoDTO getDTO(){
        return new UserShortInfoDTO("moni");
}

@PostMapping("/create")
    public void create(@RequestBody UserShortInfoDTO userDTO){
    System.out.println(userDTO.getUsername());

}
}
