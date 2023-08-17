package web.peely.Notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import web.peely.Notes.entity.User;
import web.peely.Notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("error", "Пароли не совпадают");
            return "registration";
        }

        if (userForm.getUsername().length() < 3 || userForm.getUsername().length() > 20) {
            model.addAttribute("error", "Ваш логин должен сожержать минимум 3 символа и максимум 20 символов");
            return "registration";
        }

        if (userForm.getPassword().length() < 3 || userForm.getPassword().length() > 20) {
            model.addAttribute("error", "Ваш пароль должен сожержать минимум 3 символа и максимум 20 символов");
            return "registration";
        }

        if (!userService.saveUser(userForm)){
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/login";
    }
}