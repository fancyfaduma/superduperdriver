package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/files")
public class FileController {

    FileService fileService;
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }


    @PostMapping("/upload")
    public RedirectView upload(@RequestParam("fileUpload") MultipartFile fileUpload, Principal principal, RedirectAttributes redirectAttributes){
        if(fileUpload == null || fileUpload.isEmpty()){
            redirectAttributes.addFlashAttribute("error", "Upload a file.");
            return new RedirectView("/home/files");
        }
        User user = userService.getUserByUserName(principal.getName());

        int row;

        if(fileService.alreadyUploadedOrNone(fileUpload.getOriginalFilename(), user.getUserId())){
            redirectAttributes.addFlashAttribute("error", "Filename already taken or empty.");
            return new RedirectView("/home/files");
        }
        else{
            try{
                File file = new File(null, fileUpload.getOriginalFilename(), fileUpload.getContentType(),
                        String.valueOf(fileUpload.getSize()), user.getUserId(), fileUpload.getBytes());
                row = fileService.upload(file);

            }
            catch(IOException e){
                redirectAttributes.addFlashAttribute("error", "Problem with uploading the file.");
                return new RedirectView("/home/files");
            }
            catch(MaxUploadSizeExceededException e){
                redirectAttributes.addFlashAttribute("error", "The file exceeds the max limit.");
                return new RedirectView("/home/files");
            }
        }

        if(row < 0){
            redirectAttributes.addFlashAttribute("error", "There was a problem with uploading the file.");
            return new RedirectView("/home/files");
        }

        redirectAttributes.addFlashAttribute("success", "Upload a file.");


        return new RedirectView("/home/files");
    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable int fileId, RedirectAttributes redirectAttributes){
        File file = fileService.getFileById(fileId);
        if(file == null){
            redirectAttributes.addFlashAttribute("error", "There was an error downloading the file.");
        }

        HttpHeaders response = new HttpHeaders();
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        response.setContentDisposition(ContentDisposition.builder("attachment").filename(file.getFilename()).build());
        response.setContentLength(Long.parseLong(file.getFilesize()));

        redirectAttributes.addFlashAttribute("success", "File successfully saved.");
        return new ResponseEntity<>(file.getFiledata(), response, HttpStatus.OK);
    }

    @GetMapping("/delete/{fileId}")
    public RedirectView delete(@PathVariable int fileId, RedirectAttributes redirectAttributes){
        fileService.deleteFile(fileId);
        redirectAttributes.addFlashAttribute("success", "File successfully deleted.");
        return new RedirectView("/home/files");
    }

}
