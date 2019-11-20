package attendance.system.central.models.entities;

import attendance.system.central.models.constants.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

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
    @JsonIgnore
    private UserType userType;

    public AuthorizationEntity() {

    }

    public AuthorizationEntity(String id, String password, UserType userType) {
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "AuthorizationEntity{" +
                ", id='" + id + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorizationEntity)) return false;
        AuthorizationEntity that = (AuthorizationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                userType == that.userType;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
