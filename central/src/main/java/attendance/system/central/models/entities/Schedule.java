package attendance.system.central.models.entities;

import attendance.system.central.models.constants.Times;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long rowId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Map<Times, Course> classes;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Map<Times, Course> getClasses() {
        return classes;
    }

    public void setClasses(Map<Times, Course> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "rowId=" + rowId +
                ", classes=" + classes +
                '}';
    }
}
