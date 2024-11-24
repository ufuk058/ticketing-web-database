package com.webDb.service.impl;

import com.webDb.dto.TaskDTO;
import com.webDb.dto.UserDTO;
import com.webDb.entity.Task;
import com.webDb.enums.Status;
import com.webDb.mapper.MapperUtil;
import com.webDb.repository.TaskRepository;
import com.webDb.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public TaskDTO findById(Long id) {

        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){
            return mapperUtil.convert(task,TaskDTO.class);
        }
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(task->mapperUtil.convert(task,TaskDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO task) {
        task.setAssignedDate(LocalDate.now());
        task.setTaskStatus(Status.OPEN);
        Task convertedTask=mapperUtil.convert(task,Task.class);
        taskRepository.save(convertedTask);

    }

    @Override
    public void update(TaskDTO dto) {

       Optional<Task> foundTask=taskRepository.findById(dto.getId());
       Task convertedTask =mapperUtil.convert(dto,Task.class);

       if(foundTask.isPresent()){
           convertedTask.setAssignedDate(foundTask.get().getAssignedDate());
           convertedTask.setTaskStatus(foundTask.get().getTaskStatus());
           taskRepository.save(convertedTask);
       }

    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask= taskRepository.findById(id);

        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }

    }

    @Override
    public int totalNonCompletedTask(String projectCode) {

        return taskRepository.totalNonCompletedTask(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTask(projectCode);
    }
}
