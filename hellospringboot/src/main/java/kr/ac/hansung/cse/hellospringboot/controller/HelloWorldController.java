package kr.ac.hansung.cse.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String sayHello() {
        return "index";
    }

    @GetMapping("/hi")
    public String sayHi(Model model) {
        model.addAttribute("msg","hello world");
        return "index";
    }
}
