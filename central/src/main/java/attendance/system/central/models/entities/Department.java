package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

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
    private Set<Section> sections;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private Set<Course> courses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private Set<Student> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private Set<Teacher> teachers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    @JsonIgnore
    private Set<Room> rooms;

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

    public Set<Section> getSections() {
        return sections;
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
