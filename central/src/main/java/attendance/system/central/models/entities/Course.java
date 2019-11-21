package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "courses")
public class Course {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @Column(nullable = false, unique = true, updatable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Byte semester;

    @Column(nullable = false)
    @JsonIgnore
    private Boolean valid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    @JsonIgnore
    private Set<Teacher> teachers;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Byte getSemester() {
        return semester;
    }

    public void setSemester(Byte semester) {
        this.semester = semester;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}
