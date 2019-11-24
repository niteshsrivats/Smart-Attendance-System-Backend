package attendance.system.central.models.entities;

import attendance.system.central.models.constants.Times;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name="schedules")
public class Schedule {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @ManyToMany
    private Map<Times, Course> classes;
}
