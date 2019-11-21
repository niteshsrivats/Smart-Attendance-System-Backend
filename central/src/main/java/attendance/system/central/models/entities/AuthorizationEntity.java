package attendance.system.central.models.entities;

import attendance.system.central.models.constants.EntityType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "entities")
public class AuthorizationEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String id;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityType type;

    public AuthorizationEntity() {

    }

    public AuthorizationEntity(String id, String password, EntityType type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }
}
