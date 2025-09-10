package com.example.BulletFile.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.BulletFile.service.FileService;
import com.example.BulletFile.model.FileModel;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/files")
public class filecontroller {

    @Autowired
    private FileService fileService;

    // Custom login page
    @GetMapping()
    public String login() {
        return "home";
    }

    // List files only for the logged-in user
 
@GetMapping("/home")
public String listFiles(Model model, @AuthenticationPrincipal Object principal) {
    String userId = "Unknown";
    String displayName = "Unknown User";

    if (principal instanceof OidcUser oidcUser) {
        // Google OIDC
        userId = "google:" + oidcUser.getEmail(); // unique key
        displayName = oidcUser.getFullName() != null ? oidcUser.getFullName() : oidcUser.getEmail();
    } else if (principal instanceof OAuth2User oauth2User) {
        // GitHub OAuth2
        userId = "github:" + oauth2User.getAttribute("login"); // unique key
        displayName = oauth2User.getAttribute("name") != null
                ? oauth2User.getAttribute("name")
                : oauth2User.getAttribute("login");
    }

    // Fetch files by unique ID
    List<FileModel> files = fileService.getFilesByUser(userId);

    model.addAttribute("files", files);
    model.addAttribute("displayName", displayName); // show friendly name in UI
    model.addAttribute("uploadSuccess", false);

    return "listfile";
}




    // Upload file and automatically assign uploadedBy
  @PostMapping("/upload")
public String uploadFile(@RequestParam("file") MultipartFile file,
                         @AuthenticationPrincipal Object principal) throws IOException {
    if (file == null || file.isEmpty()) {
        return "redirect:/files/home?error=NoFile";
    }

    String userId = "Unknown";
    String displayName = "Unknown User";

    if (principal instanceof OidcUser oidcUser) {
        userId = "google:" + oidcUser.getEmail();
        displayName = oidcUser.getFullName() != null ? oidcUser.getFullName() : oidcUser.getEmail();
    } else if (principal instanceof OAuth2User oauth2User) {
        userId = "github:" + oauth2User.getAttribute("login");
        displayName = oauth2User.getAttribute("name") != null
                ? oauth2User.getAttribute("name")
                : oauth2User.getAttribute("login");
    }

    // Save both ID (for filtering) and displayName (for UI)
    fileService.uploadFile(file, userId, displayName);

    return "redirect:/files/home?success=true";
}


    @GetMapping("/share/{id}")
    public String shareFile(@PathVariable("id") int id, Model model) {
        ResponseEntity<?> fileModel = fileService.shareFile(id);
        if (fileModel.hasBody()) {
            String currentUrl = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            model.addAttribute("shareUrl", currentUrl);
            model.addAttribute("file", fileModel.getBody());
            return "sharefile";
        } else {
            return "redirect:/files";
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable("id") int id) {
        return fileService.getFile(id);
    }

    @PostMapping("/delete/{id}")
    public String deleteFile(@PathVariable int id) {
        ResponseEntity<?> file = fileService.deleteFile(id);
        if (file.hasBody()) {
            return "redirect:/files/home";
        } else {
            return "redirect:/files";
        }
    }
}
