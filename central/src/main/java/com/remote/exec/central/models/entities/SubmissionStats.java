package com.remote.exec.central.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "submission_stats")
public class SubmissionStats {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(updatable = false)
    private String input;

    @Column(updatable = false)
    private String output;

    @Column(updatable = false)
    private Boolean success;

    @Column(updatable = false)
    private Long runTime = null;

    @Column(updatable = false)
    private Long memory = null;
}
