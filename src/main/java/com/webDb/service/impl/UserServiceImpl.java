package com.webDb.service.impl;

import com.webDb.dto.RoleDTO;
import com.webDb.dto.UserDTO;
import com.webDb.entity.User;
import com.webDb.mapper.MapperUtil;
import com.webDb.mapper.UserMapper;
import com.webDb.repository.UserRepository;
import com.webDb.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final UserMapper userMapper;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
       // this.userMapper = userMapper;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList=userRepository.findAll();

       // return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
        return userList.stream().map(user->mapperUtil.convert(user, UserDTO.class)).collect(Collectors.toList());
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

