package com.webDb.repository;

import com.webDb.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task,Long> {
   @Query("Select Count(t) from Task t where t.project.projectCode = ?1 AND t.taskStatus<>'COMPLETE' ")
    int totalNonCompletedTask(String projectCode);

   @Query("Select Count(t) from Task t where t.project.projectCode =?1 And t.taskStatus='COMPLETE'")
    int totalCompletedTask(String projectCode);
}
