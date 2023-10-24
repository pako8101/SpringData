package bg.softuni.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("firstName","Simona");
        return modelAndView;
    }

    @GetMapping("/home")
    public String home(){

        return "home";
    }

}
