package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "questions")
public class Question {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false)
    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean enable;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<TestCase> testCases;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Submission> submissions;
}
