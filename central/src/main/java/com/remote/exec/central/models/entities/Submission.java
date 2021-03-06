package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remote.exec.central.models.constants.Compiler;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Compiler compiler;

    @Column(nullable = false, updatable = false)
    private String code;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubmissionStats> stats;
}
