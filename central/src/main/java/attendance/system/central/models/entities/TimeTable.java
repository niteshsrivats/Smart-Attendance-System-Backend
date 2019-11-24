package attendance.system.central.models.entities;

import attendance.system.central.models.constants.Days;
import attendance.system.central.models.constants.Times;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Entity(name = "timetables")
public class TimeTable {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private Section section;

//    @ManyToMany(mappedBy = "timetable")
//    @ElementCollection

    @ElementCollection
    private Map<Days, Schedule> daysScheduleMap;

//    @ManyToMany
//    private Map<Days, Map<Times, Course>> schedule;
//
//    @ManyToMany
//    private Map<Times, Course> monday;
}
