package attendance.system.central.models.payload;

import javax.validation.constraints.NotBlank;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class LoginRequest {

    @NotBlank
    private String id;
    @NotBlank
    private String password;

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
}
