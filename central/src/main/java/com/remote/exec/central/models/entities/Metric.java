package com.remote.exec.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private Long requests = 0L;

    private Long executionTime = null;

    private Long memoryConsumption = null;
}
