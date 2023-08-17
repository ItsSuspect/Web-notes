package web.peely.Notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import web.peely.Notes.entity.User;
import web.peely.Notes.service.UserService;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String account(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("currentUser", userService.findUserById(userService.findIdByUsername(authentication.getName())));
        return "account";
    }

    @PostMapping("/changeProfile")
    public String changeProfile(@ModelAttribute("currentUser") User currentUser, Model model) {
        userService.updateProfile(currentUser.getId(), currentUser);
        model.addAttribute("success", "Ваши данные успешно сохранены");
        return "account";
    }

    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute("currentUser") User currentUser, @RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, Model model) {

        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Новый пароль не совпадает");
            return "account";
        }

        if (newPassword.length() < 3 || newPassword.length() > 20) {
            model.addAttribute("error", "Ваш пароль должен сожержать минимум 3 символа и максимум 20 символов");
            return "account";
        }

        if (userService.updatePassword(currentUser.getId(), currentUser, currentPassword, newPassword)) {
            model.addAttribute("error", "Неверный пароль");
            return "account";
        }

        model.addAttribute("success", "Ваш пароль успешно изменен");
        return "account";
    }
}
