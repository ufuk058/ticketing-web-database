package com.webDb.controller;
//
//import com.webDb.dto.ProjectDTO;
//import com.webDb.dto.UserDTO;
//import com.webDb.service.ProjectService;
//import com.webDb.service.UserService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/project")
//public class ProjectController {
//
//    private final ProjectService projectService;
//    private final UserService userService;
//
//    public ProjectController(ProjectService projectService, UserService userService) {
//        this.projectService = projectService;
//        this.userService = userService;
//    }
//
//    @GetMapping("/create")
//    public String createProject(Model model) {
//
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("managers", userService.findManagers());
//
//        return "project/create";
//    }
//
//    @PostMapping("/create")
//    public String insertProject(@Valid @ModelAttribute("project") ProjectDTO project,
//                                BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("managers", userService.findManagers());
//            model.addAttribute("projects", projectService.findAll());
//
//            return "/project/create";
//        }
//
//        projectService.save(project);
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/update/{projectCode}")
//    public String editProject(@PathVariable("projectCode") String projectCode, Model model) {
//
//        model.addAttribute("project", projectService.findById(projectCode));
//        model.addAttribute("projects", projectService.findAll());
//        model.addAttribute("managers", userService.findManagers());
//
//        return "project/update";
//    }
//
//
//    @PostMapping("/update")
//    public String updateProject(@Valid @ModelAttribute("project") ProjectDTO project,
//                                BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//
//            model.addAttribute("managers", userService.findManagers());
//            model.addAttribute("projects", projectService.findAll());
//
//            return "/project/update";
//        }
//
//        projectService.update(project);
//        return "redirect:/project/create";
//    }
//
//
//    @GetMapping("/delete/{projectCode}")
//    public String deleteProject(@PathVariable("projectCode") String projectCode) {
//
//        projectService.deleteById(projectCode);
//
//        return "redirect:/project/create";
//    }
//
//
//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode) {
//
//        projectService.complete(projectService.findById(projectCode));
//
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/manager/project-status")
//    private String getProjectByManager(Model model) {
//
//        UserDTO manager = userService.findById("john@cydeo.com");
//
//        model.addAttribute("projects", projectService.getCountedListOfProjectDTO(manager));
//
//        return "manager/project-status";
//
//    }
//
//    @GetMapping("/manager/complete/{projectCode}")
//    public String managerCompleteProject(@PathVariable("projectCode") String projectCode) {
//
//        projectService.complete(projectService.findById(projectCode));
//
//        return "redirect:/project/manager/project-status";
//    }
//}
