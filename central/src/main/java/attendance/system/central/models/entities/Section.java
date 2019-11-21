package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "sections")
public class Section {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private Character section;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Byte semester;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer room;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Map<Course, Teacher> courseTeacherMap;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sections")
    @JsonIgnore
    private Set<Student> students;

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

    public Character getSection() {
        return section;
    }

    public void setSection(Character section) {
        this.section = section;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Map<Course, Teacher> getCourseTeacherMap() {
        return courseTeacherMap;
    }

    public void setCourseTeacherMap(Map<Course, Teacher> courseTeacherMap) {
        this.courseTeacherMap = courseTeacherMap;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }
}
