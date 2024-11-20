package com.webDb.mapper;

import com.webDb.dto.RoleDTO;
import com.webDb.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleDTO convertToDto(Role entity){

        return  modelMapper.map(entity, RoleDTO.class);
    }

    public Role convertToEntity(RoleDTO dto){

        return modelMapper.map(dto,Role.class);
    }
}
