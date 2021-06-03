package kr.ac.hansung.cse.hellospringsecurity.controller;

import kr.ac.hansung.cse.hellospringsecurity.entity.Role;
import kr.ac.hansung.cse.hellospringsecurity.entity.User;
import kr.ac.hansung.cse.hellospringsecurity.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user, Model model) {

        if (registrationService.checkEmailExists(user.getEmail())) {
            //이메일 중복
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            //회원가입 받는 유저는 기본적으로 "ROLE_USER"
            Role role = registrationService.findByRolename("ROLE_USER");

            List<Role> userRoles = new ArrayList<>();

            //"ROLE_USER" 하나 추가
            userRoles.add(role);

            registrationService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
}
