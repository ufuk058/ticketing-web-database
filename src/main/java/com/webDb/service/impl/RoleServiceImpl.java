package com.webDb.service.impl;

import com.webDb.dto.RoleDTO;
import com.webDb.entity.Role;
import com.webDb.mapper.RoleMapper;
import com.webDb.repository.RoleRepository;
import com.webDb.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
   private final RoleRepository roleRepository;
   private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        //ask repository layer to give us a list of roles from database
        List<Role> roleList=roleRepository.findAll();
        // if provide roleList to return type it will give error because roleList is Role object but we need to
        // return RoleDTO so we need a mechanism to convert Role to RoleDTO which is RoleMapper



        return roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long Id) {
        return null;
    }
}
