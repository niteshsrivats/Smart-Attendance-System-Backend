package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @OneToOne(cascade = CascadeType.ALL, optional = false, targetEntity = AuthorizationEntity.class)
    private AuthorizationEntity entity;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Section.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Section> sections;

    @Override
    public String toString() {
        return "Student{" +
                ", entity=" + entity +
                ", name='" + name + '\'' +
                ", '" + super.toString() + '\'' +
                '}';
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

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
