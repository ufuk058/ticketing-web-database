package com.webDb.service;

import com.webDb.dto.ProjectDTO;
import com.webDb.dto.UserDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO findByProjectCode(String projectCode);
    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO project);
    void update(ProjectDTO project);
    void delete(String projectCode);

    void complete(String projectCode);

    List<ProjectDTO> listAllProjectDetails();

    List<ProjectDTO> listAllNonCompletedByAssignedManager(UserDTO user);
}
