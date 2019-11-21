package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Department department;

    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Section section;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
