package org.exo.student.controller;

import org.exo.student.model.User;
import org.exo.student.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class UserController {

    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/profil")
    public String profil(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "pages/user-profil";
    }

    @GetMapping("/profil/edit")
    public String updateUser(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "pages/user-edit";
    }

    @PostMapping("/profil/edit")
    public String updateUserPost() {
        userService.updateUser(userService.getCurrentUser());
        return "redirect:/profil";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image") MultipartFile image) throws IOException {

        String location = "src/main/resources/static/uploads/";
        String fileName = image.getOriginalFilename();
        Path destinationFile = Paths.get(location).resolve(image.getOriginalFilename()).toAbsolutePath();
        InputStream inputStream = image.getInputStream();
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);


        User currentUser = userService.getCurrentUser();
        currentUser.setImagePath("/uploads/" + fileName);
        userService.updateUser(currentUser);

        return "redirect:/profil";
    }
}
