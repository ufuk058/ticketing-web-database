package com.webDb.repository;

import com.webDb.entity.Project;
import com.webDb.entity.Task;
import com.webDb.entity.User;
import com.webDb.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
   @Query("Select Count(t) from Task t where t.project.projectCode = ?1 AND t.taskStatus<>'COMPLETE' ")
    int totalNonCompletedTask(String projectCode);

   @Query("Select Count(t) from Task t where t.project.projectCode =?1 And t.taskStatus='COMPLETE'")
    int totalCompletedTask(String projectCode);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User employee);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User employee);
}
