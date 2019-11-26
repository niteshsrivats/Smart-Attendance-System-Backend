package attendance.system.central.models.constants;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public enum Times {
    FIRST("08:00 - 08:55"),
    SECOND("08:55 - 09:50"),
    THIRD("09:50 - 10:45"),
    FOURTH("11:15 - 12:10"),
    FIFTH("12:10 - 01:05"),
    SIXTH("02:00 - 02:55"),
    SEVENTH("02:55 - 03:50"),
    EIGHTH("03:50 - 04:45");

    private final String time;

    Times(String time) {
        this.time = time;
    }

    @JsonValue
    public String getTime() {
        return time;
    }
}

//"17CSE5ADA": "teacher-cse-id-2",
//"16CSE5SE": "teacher-cse-id-3",
//"19CSE5IOT": "teacher-cse-id-5",
//"15CSE5DS": "teacher-cse-id-1",
//"18CSE5PWP": "teacher-cse-id-4"

