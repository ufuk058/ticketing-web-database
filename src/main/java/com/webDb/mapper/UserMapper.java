package com.webDb.mapper;

import com.webDb.dto.UserDTO;
import com.webDb.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO convertToDto(User entity){

        return modelMapper.map(entity, UserDTO.class);
    }

    public User convertToEntity(UserDTO dto){
        return modelMapper.map(dto,User.class);
    }
}
