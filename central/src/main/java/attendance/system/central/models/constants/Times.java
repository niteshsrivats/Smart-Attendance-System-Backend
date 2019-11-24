package attendance.system.central.models.constants;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public enum Times {
    FIRST("08:00 - 08:55"),
    SECOND("08:55 - 09:50");

    private final String time;

    Times(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
