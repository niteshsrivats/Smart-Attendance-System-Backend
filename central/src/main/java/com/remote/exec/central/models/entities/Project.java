package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.remote.exec.central.models.constants.ProjectProperty;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(nullable = false, unique = true, updatable = false)
    private String apiId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean enable = false;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Metric metric;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<ProjectProperty> projectProperties;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Submission> submissions = null;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public List<ProjectProperty> getProjectProperties() {
        return projectProperties;
    }

    public void setProjectProperties(List<ProjectProperty> projectProperties) {
        this.projectProperties = projectProperties;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}
