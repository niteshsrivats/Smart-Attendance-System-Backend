package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "devices")
public class Device {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @OneToOne(cascade = CascadeType.ALL, optional = false, targetEntity = AuthorizationEntity.class)
    private AuthorizationEntity entity;

    @Column(nullable = false)
    private String name;

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

    public String getId() {
        return entity.getId();
    }
}
