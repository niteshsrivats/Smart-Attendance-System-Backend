package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remote.exec.central.models.constants.CountType;

import javax.persistence.*;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "counts")
public class Count {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CountType countType;

    @Column(nullable = false)
    private Long count;
}
