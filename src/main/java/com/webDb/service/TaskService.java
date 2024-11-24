package com.webDb.service;

import com.webDb.dto.ProjectDTO;
import com.webDb.dto.TaskDTO;
import com.webDb.entity.Task;
import com.webDb.enums.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long id);

    List<TaskDTO> listAllTasks();

    void save(TaskDTO task);
    void update(TaskDTO task);
    void delete(Long id);

    int totalNonCompletedTask(String projectCode);

    int totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO project);

    void completeByProject(ProjectDTO project);

    List<TaskDTO> listAllTasksByStatusIsNot(Status complete);
}
