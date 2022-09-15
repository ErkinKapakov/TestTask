package com.example.testtask.services.impl;

import com.example.testtask.microservices.FileServiceFeign;
import com.example.testtask.models.dtos.UserDto;
import com.example.testtask.models.entities.User;
import com.example.testtask.models.enums.Status;
import com.example.testtask.models.mappers.UserMapper;
import com.example.testtask.models.requests.CreateUserRequest;
import com.example.testtask.models.responses.Response;
import com.example.testtask.models.responses.UserUpdateResponse;
import com.example.testtask.repositories.UserRepo;
import com.example.testtask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FileServiceFeign fileServiceFeign;

    private final UserMapper userMapper;

    public UserServiceImpl() {
        this.userMapper = UserMapper.INSTANCE;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userRepo.save(user);
        userDto = userMapper.toDto(user);
        return userDto;
    }

    @Override
    public UserDto saveUser(CreateUserRequest createUserRequest) {
        UserDto userDto = new UserDto(
                null,
                createUserRequest.getName(),
                createUserRequest.getEmail(),
                createUserRequest.getImageUri(),
                createUserRequest.getStatus()
        );
        return save(userDto);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return userMapper.toDto(user);
    }

    public User getEntityById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        return user;
    }

    @Override
    public UserDto update(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        if(userRepo.existsById(user.getId())){
            userRepo.save(user);
            return userMapper.toDto(user);
        }
        return null;
    }

    @Override
    public UserUpdateResponse updateStatus(Long id, Status status) {
        User user = getEntityById(id);
        Status previousStatus = user.getStatus();
        user.setStatus(status);
        userRepo.save(user);

        return new UserUpdateResponse(user.getId(), status, previousStatus);
    }

    @Override
    public String uploadPicture(MultipartFile file) {
        Response response = fileServiceFeign.upload(file);
        return response.getDownloadUri();
    }

    @Override
    public List<UserDto> checkUsers(Status status) {
        List<User> users = userRepo.findAllByStatus(status);
        return users.stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }


}
