package com.tskifyX.TaskifyX.repository;

import com.tskifyX.TaskifyX.model.Project;
import com.tskifyX.TaskifyX.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    List<Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName, User user);

//    //this query method  you have complicated query then you need to write this
//    @Query("SELECT p FROM Project p join p.team t where t=:user")
//    List<Project> findProjectByTeam(@Param("user") User user);

     List<Project> findByTeamContainingOrOwner(User user, User owner);



}
