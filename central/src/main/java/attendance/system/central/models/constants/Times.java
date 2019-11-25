package attendance.system.central.models.constants;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public enum Times {
    FIRST("08:00 - 08:55"),
    SECOND("08:55 - 09:50"),
    THIRD("11:15 - 12:10"),
    FOURTH("12:10 - 01:05"),
    FIFTH("02:00 - 02:55"),
    SIXTH("02:55 - 03:50"),
    SEVENTH("03:50 - 04:45");

    private final String time;

    Times(String time) {
        this.time = time;
    }

    @JsonValue
    public String getTime() {
        return time;
    }
}
