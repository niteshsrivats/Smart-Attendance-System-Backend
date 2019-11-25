package attendance.system.central.models.entities;

import attendance.system.central.models.constants.EntityType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "students")
public class Student {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AuthorizationEntity entity;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer graduationYear;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Section> sections;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @NotNull
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Map<Course, Attendance> courseAttendance;

    public String getId() {
        return entity.getId();
    }

    public EntityType getType() {
        return entity.getType();
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public AuthorizationEntity getEntity() {
        return entity;
    }

    public void setEntity(AuthorizationEntity entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Map<Course, Attendance> getCourseAttendance() {
        return courseAttendance;
    }

    public void setCourseAttendance(Map<Course, Attendance> courseAttendance) {
        this.courseAttendance = courseAttendance;
    }
}
