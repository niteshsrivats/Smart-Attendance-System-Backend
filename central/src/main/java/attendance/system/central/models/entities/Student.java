package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "students")
public class Student {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @OneToOne(optional = false, targetEntity = AuthorizationEntity.class)
    @JoinColumn(nullable = false, referencedColumnName = "id")
    private String id;
}
