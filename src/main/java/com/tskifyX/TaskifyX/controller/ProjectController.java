package com.tskifyX.TaskifyX.controller;

import com.tskifyX.TaskifyX.model.Project;
import com.tskifyX.TaskifyX.model.User;
import com.tskifyX.TaskifyX.response.MessageResponse;
import com.tskifyX.TaskifyX.service.ProjectSercvice;
import com.tskifyX.TaskifyX.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// project pause at 3:03:25 time video
@RequestMapping("/api/projects")
@RestController
public class ProjectController {
    @Autowired
    private ProjectSercvice projectSercvice;

    @Autowired
    private UserService  userService;

    @GetMapping
    public ResponseEntity<List<Project>>getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestParam("Authorization") String jwt
    ) throws Exception {
        Optional<User> user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectSercvice.getProjectByTeam(user.orElse(null),category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectsById(
            @PathVariable Long projectId,
            @RequestParam("Authorization") String jwt
    ) throws Exception {
        Optional<User> user = userService.findUserProfileByJwt(jwt);
        Project project = projectSercvice.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Project>createProject(
            @RequestParam("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {
        Optional<User> user = userService.findUserProfileByJwt(jwt);
        Project createdproject = projectSercvice.createProject(project, user.orElse(null));
        return new ResponseEntity<>(createdproject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId")
    public ResponseEntity<Project>updateProject(
            @PathVariable Long projectId,
            @RequestParam("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {
        Optional<User> user = userService.findUserProfileByJwt(jwt);
        Project updatedproject = projectSercvice.udateProject(project, projectId);
        return new ResponseEntity<>(updatedproject , HttpStatus.OK);
    }

    @DeleteMapping("/{projectId")
    public ResponseEntity<MessageResponse>deleteProject(
            @PathVariable Long projectId,
            @RequestParam("Authorization") String jwt

    ) throws Exception {
        Optional<User> user = userService.findUserProfileByJwt(jwt);
        projectSercvice.deleteProject(projectId, user.get().getId());
        MessageResponse res =  new MessageResponse("project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(
            @RequestParam(required = false) String keyword,

            @RequestParam("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Project> projects = projectSercvice.searchProject(keyword,user);
        return new ResponseEntity<>(projects, HttpStatus.OK);

    }
