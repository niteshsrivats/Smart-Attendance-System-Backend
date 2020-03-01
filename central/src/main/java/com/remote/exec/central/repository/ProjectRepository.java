package com.remote.exec.central.repository;

import com.remote.exec.central.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findProjectById(String id);

    Boolean existsById(String id);
}
