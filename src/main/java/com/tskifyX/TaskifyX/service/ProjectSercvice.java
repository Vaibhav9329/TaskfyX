package com.tskifyX.TaskifyX.service;

import com.tskifyX.TaskifyX.model.Chat;
import com.tskifyX.TaskifyX.model.Project;
import com.tskifyX.TaskifyX.model.User;

import java.util.List;

public interface ProjectSercvice {
    Project createProject(Project project, User user)throws Exception;

    List<Project> getProjectByTeam(User user,String category,String tag)throws Exception;

    Project getProjectById(Long projectId)throws Exception;


    void deleteProject(Long project, Long userId)throws Exception;

    Project udateProject(Project updatedProject, Long id)throws  Exception;

    void addUserToProject(Long projectId, User userId)throws Exception;

    void removeUserFromeProject(Long projectId, User userId)throws Exception;

    Chat getChatByProject(Long project)throws Exception;

    List<Project> searchProject(String keyword,User user)throws Exception;
}
