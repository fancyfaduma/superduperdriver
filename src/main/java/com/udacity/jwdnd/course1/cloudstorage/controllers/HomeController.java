package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private UserService userService;
    private FileService fileService;
    private CredentialService credentialService;
    private NoteService noteService;

    public HomeController(UserService userService, FileService fileService, CredentialService credentialService, NoteService noteService) {
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.noteService = noteService;
    }


    @GetMapping()
    public String getHomePage(Model model, Authentication authentication){
        String username = authentication.getName();
        User user = userService.getUserByUserName(username);
        int userId = user.getUserId();

        List<File> files = fileService.getFilesByUser(userId);
        model.addAttribute("files", files);

        List<Note> notes = noteService.getNotesByUser(userId);
        model.addAttribute("notes", notes);

        List<Credential> credentials = credentialService.getCredentialsByUser(userId);
        model.addAttribute("credentials", credentials);
        return "home";
    }
}
