package kr.ac.hansung.cse.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String sayHello(){
        return "index";
    }

    @GetMapping("/hi")
    public String sayHi(){
        return "index";
    }
}
