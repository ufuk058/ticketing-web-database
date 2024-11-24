package com.webDb.controller;

import com.webDb.dto.TaskDTO;
import com.webDb.enums.Status;
import com.webDb.service.ProjectService;
import com.webDb.service.TaskService;
import com.webDb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(UserService userService, ProjectService projectService, TaskService taskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new TaskDTO());
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("tasks", taskService.listAllTasks());
        return "task/create";
    }

    @PostMapping("/create")
    public String insertTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjects());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAllTasks());

            return "task/create";
        }

        taskService.save(task);

        return "redirect:/task/create";
    }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){

        taskService.delete(id);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{id}")
    public String editTask(@PathVariable("id") Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("employees", userService.listAllByRole("employee"));
        model.addAttribute("projects", projectService.listAllProjects());
        model.addAttribute("tasks", taskService.listAllTasks());

        return "task/update";
    }

//    @PostMapping("/update/{taskId}")
//    public String updateTask(@PathVariable("taskId") Long id, @ModelAttribute("task") TaskDTO task){
//
//        task.setId(id);
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }

    @PostMapping("/update/{id}")
    public String updateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("projects", projectService.listAllProjects());
            model.addAttribute("employees", userService.listAllByRole("employee"));
            model.addAttribute("tasks", taskService.listAllTasks());

            return "task/update";
        }

        taskService.update(task);

        return "redirect:/task/create";
    }

    @GetMapping("employee/pending-tasks")
    public String employeePendingTasks(Model model){

        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));

        return "task/pending-tasks";
    }

    @GetMapping("employee/archive")
    public String employeeArchivedTasks(Model model){

        model.addAttribute("tasks", taskService.litsAllTasksByStatus(Status.COMPLETE));

        return "task/archive";
    }

    @GetMapping("employee/edit/{id}")
    public String employeeEditTask(@PathVariable("id") Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));

        return "task/status-update";
    }

    @PostMapping("/employee/update/{id}")
    public String employeeUpdateTask(@Valid @ModelAttribute("task") TaskDTO task, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("statuses", Status.values());
            model.addAttribute("tasks", taskService.listAllTasksByStatusIsNot(Status.COMPLETE));

            return "task/status-update";
        }

        taskService.update(task);

        return "redirect:/task/employee/pending-tasks";
    }


}
