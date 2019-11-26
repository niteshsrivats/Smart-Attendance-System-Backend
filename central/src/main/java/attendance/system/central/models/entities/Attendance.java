package attendance.system.central.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private  Map<Long, Boolean> attendance;

    public Attendance() {
        this.attendance = new HashMap<>();
    }

    public Map<Long, Boolean> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<Long, Boolean> attendance) {
        this.attendance = attendance;
    }
}
