package com.webDb.repository;

import com.webDb.dto.ProjectDTO;
import com.webDb.dto.UserDTO;
import com.webDb.entity.Project;
import com.webDb.entity.User;
import com.webDb.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectCode(String projectCode);

    List<Project> findAllByAssignedManager(User manager);

    List<Project> findAllByProjectStatusIsNotAndAssignedManager(Status status, User manager);
}
