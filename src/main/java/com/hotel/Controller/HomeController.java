package com.hotel.Controller;




import com.hotel.Repo.UserRepository;
import com.hotel.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/doregister")
    public String doRegister(@ModelAttribute User user,
                             @RequestParam(value = "aggreement", defaultValue = "false") boolean agreement,
                             Model model, HttpSession session) {
        try {
            if (!agreement) {
                throw new Exception("You have not agreed to the terms and conditions.");
            }

            user.setRole("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = this.userRepository.save(user);
            session.setAttribute("msg", "Registration successful");

            // Redirect to login page or any other page
            return "redirect:/register";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("msg", "Something went wrong");
            return "register";
        }
    }
}
