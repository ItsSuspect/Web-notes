package web.peely.Notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.peely.Notes.entity.Note;
import web.peely.Notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class NoteController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",  method = RequestMethod.GET)
    public String getNote(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return "login";
        model.addAttribute("noteForm", userService.getNote(authentication.getName()));
        return "index";
    }

    @RequestMapping(value = "/",  method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void postNote(@ModelAttribute("noteForm") Note noteForm) {
        userService.updateNote(noteForm);
    }
}
