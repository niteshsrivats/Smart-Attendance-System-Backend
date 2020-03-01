package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remote.exec.central.models.constants.ProjectType;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "projects")
public class Project {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Enumerated(EnumType.STRING)
    private ProjectType projectType;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private API api;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Submission> submissions = null;
}
