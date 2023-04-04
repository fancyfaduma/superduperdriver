package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getSignupPage(){
        return "signup";
    }

    @PostMapping()
    public RedirectView signUpUser(@ModelAttribute User user, RedirectAttributes redirectAttributes){
        if(userService.isUsernameTaken(user.getUsername())){
            redirectAttributes.addFlashAttribute("signupError", "Username is already taken.");
            return new RedirectView("/signup");
        }

        int newUser = userService.add(user);
        if(newUser < 0){
            redirectAttributes.addFlashAttribute("signupError", "There was a problem signing you up.");
            return new RedirectView("/signup");
        }

        redirectAttributes.addFlashAttribute("signupSuccessful", true);
        return new RedirectView("/login");

    }

}
