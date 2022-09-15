package com.example.testtask.controllers;

import com.example.testtask.models.dtos.UserDto;
import com.example.testtask.models.enums.Status;
import com.example.testtask.models.requests.CreateUserRequest;
import com.example.testtask.models.responses.UserUpdateResponse;
import com.example.testtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    private String uploadPicture(@RequestPart MultipartFile file){
        return userService.uploadPicture(file);
    }

    @PostMapping("/add")
    private UserDto addUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.saveUser(createUserRequest);
    }

    @GetMapping("/get")
    private UserDto getUser(@RequestParam Long id) {
        return userService.getById(id);
    }

    @PostMapping("/update")
    private UserUpdateResponse updateStatus(@RequestParam Long id, @RequestParam Status status){
        return userService.updateStatus(id, status);
    }

    @PostMapping("/check")
    private List<UserDto> checkUsers(@RequestParam Status status){
        return userService.checkUsers(status);
    }
}
