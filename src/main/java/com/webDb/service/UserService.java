package com.webDb.service;

import com.webDb.dto.UserDTO;
import com.webDb.entity.Role;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO user);
    void update(UserDTO user);
    void delete(String username);

    List<UserDTO> listAllByRole(String role);
}
