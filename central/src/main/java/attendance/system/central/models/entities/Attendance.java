package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> time;

    public Attendance() {
        this.time = new ArrayList<>();
    }

    public List<Long> getTime() {
        return time;
    }

    public void setTime(List<Long> time) {
        this.time = time;
    }
}
