package com.example.practica2.controllers;

import com.example.practica2.models.Role;
import com.example.practica2.models.User;
import com.example.practica2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }

    @PostMapping("/registration")
    private String Reg(User user, Model model)
    {
        User user_from_db = userRepository.findUserByUsername(user.getUsername());
        if(user_from_db != null)
        {
            model.addAttribute("message","Пользователь с таким логином уже существует");
            return "regis";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }
}