package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "departments")
public class Department {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<Section> sections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<Student> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private List<Teacher> teachers;

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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
