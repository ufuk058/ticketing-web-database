package com.webDb.service.impl;

import com.webDb.dto.ProjectDTO;
import com.webDb.dto.UserDTO;
import com.webDb.enums.Status;
import com.webDb.service.ProjectService;
import com.webDb.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public ProjectDTO save(ProjectDTO project) {

        if (project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }

        return super.save(project.getProjectCode(), project);
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void update(ProjectDTO project) {

        if (project.getProjectStatus()==null){
            project.setProjectStatus(findById(project.getProjectCode()).getProjectStatus());
        }

        super.update(project.getProjectCode(), project);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjectDTO(UserDTO manager) {

        List<ProjectDTO> projectList = findAll()
                .stream()
                .filter(project -> project.getAssignedManager().equals(manager)) //John
                .map(project -> {

                    int completeTaskCounts = (int) taskService.findAll().stream()
                                    .filter(task -> task.getProject().equals(project) && task.getTaskStatus()
                                    == Status.COMPLETE)
                                            .count();

                    int unfinishedTaskCounts = (int) taskService.findAll().stream()
                            .filter(task -> task.getProject().equals(project) && task.getTaskStatus()
                                    != Status.COMPLETE)
                            .count();

                    project.setCompleteTaskCounts(completeTaskCounts);
                    project.setUnfinishedTaskCounts(unfinishedTaskCounts);

                    return project;

                })
                .collect(Collectors.toList());

        return projectList;
    }
}
