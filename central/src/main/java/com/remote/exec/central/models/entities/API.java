package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "apis")
public class API {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private Boolean enable;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<APIProperty> apiProperties;
}
