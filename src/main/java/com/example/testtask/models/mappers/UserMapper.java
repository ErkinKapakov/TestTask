package com.example.testtask.models.mappers;

import com.example.testtask.models.dtos.UserDto;
import com.example.testtask.models.entities.User;
import com.example.testtask.models.mappers.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends CrudMapper<User, UserDto> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
