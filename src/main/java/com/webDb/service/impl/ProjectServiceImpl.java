package com.webDb.service.impl;

import com.webDb.dto.ProjectDTO;
import com.webDb.entity.Project;
import com.webDb.enums.Status;
import com.webDb.mapper.MapperUtil;
import com.webDb.repository.ProjectRepository;
import com.webDb.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperUtil mapperUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;

    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        Project project =projectRepository.findByProjectCode(projectCode);

        return mapperUtil.convert(project,ProjectDTO.class);
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        List<Project> list= projectRepository.findAll(Sort.by("projectCode"));
        return list.stream().map(project ->mapperUtil.convert(project,ProjectDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO project) {

        project.setProjectStatus(Status.OPEN);
        Project convertedProject= mapperUtil.convert(project,Project.class);
        projectRepository.save(convertedProject);
    }

    @Override
    public void update(ProjectDTO project) {
        Project foundProject=projectRepository.findByProjectCode(project.getProjectCode());
        Project updatedProject=mapperUtil.convert(project,Project.class);
        updatedProject.setId(foundProject.getId());
        updatedProject.setProjectStatus(foundProject.getProjectStatus());

        projectRepository.save(updatedProject);
    }

    @Override
    public void delete(String projectCode) {

        Project p1= projectRepository.findByProjectCode(projectCode);
        p1.setIsDeleted(true);
        p1.setProjectCode(p1.getProjectCode()+"-"+p1.getId());
        projectRepository.save(p1);


    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }
}
