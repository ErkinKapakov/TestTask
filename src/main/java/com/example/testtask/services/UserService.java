package com.example.testtask.services;

import com.example.testtask.models.dtos.UserDto;
import com.example.testtask.models.enums.Status;
import com.example.testtask.models.requests.CreateUserRequest;
import com.example.testtask.models.responses.Response;
import com.example.testtask.models.responses.UserUpdateResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    UserDto getById(Long Id);

    UserDto update(UserDto userDto);

    UserUpdateResponse updateStatus(Long id, Status status);

    String uploadPicture(MultipartFile file);

    List<UserDto> checkUsers(Status status);

    UserDto saveUser(CreateUserRequest createUserRequest);
}
