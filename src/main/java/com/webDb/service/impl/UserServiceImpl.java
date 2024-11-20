package com.webDb.service.impl;

import com.webDb.dto.UserDTO;
import com.webDb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> listAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO user) {

    }

    @Override
    public void update(UserDTO user) {

    }

    @Override
    public void delete(String username) {

    }
}

