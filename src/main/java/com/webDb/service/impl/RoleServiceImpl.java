package com.webDb.service.impl;

import com.webDb.dto.RoleDTO;
import com.webDb.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Component => @Service, @Controller, ...
@Service
public class RoleServiceImpl extends AbstractMapService<RoleDTO, Long> implements RoleService {

    @Override
    public RoleDTO save(RoleDTO role) {
        return super.save(role.getId(), role);
    }

    @Override
    public RoleDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<RoleDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void update(RoleDTO role) {
        super.update(role.getId(), role);
    }
}
