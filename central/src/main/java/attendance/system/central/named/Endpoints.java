package attendance.system.central.named;

import static attendance.system.central.named.Placeholders.VersionPrefix;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class Endpoints {

    public final static String Semesters = VersionPrefix + "semesters";

    public static class Users {
        public final static String Base = VersionPrefix + "users";
        public final static String GetById = Base + "/{id}";

        public final static String Sections = GetById + "/sections";
    }

    public static class Students {
        public final static String Base = VersionPrefix + "students";
        public final static String GetById = Base + "/{id}";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/login";

        public final static String Sections = GetById + "/sections";
        public final static String GetCourseAttendance = GetById + "/courses/attendance";
        public final static String CourseAttendance = GetById + "/courses/{courseId}";
    }

    public static class Teachers {
        public final static String Base = VersionPrefix + "teachers";
        public final static String GetById = Base + "/{id}";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/login";

        public final static String ExplicitIdSections = GetById + "/sections";
        public final static String ExplicitIdCourses = GetById + "/courses";
    }

    public static class Devices {
        public final static String Base = VersionPrefix + "devices";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/login";

        public final static String Sections = Base + "/sections";
    }

    public static class Sections {
        public final static String Base = VersionPrefix + "sections";
        public final static String GetById = Base + "/{id}";
        public final static String Students = GetById + "/students";
        public final static String Courses = GetById + "/courses";
        public final static String CourseTeacherPair = GetById + "/teachers/{teacherId}";
        public final static String Timetable = GetById + "/timetable";
        public final static String TimetableSchedule = GetById + "/timetable/{day}";
    }

    public static class Departments {
        public final static String Base = VersionPrefix + "departments";
        public final static String GetById = Base + "/{id}";
        public final static String Courses = GetById + "/courses";
        public final static String Sections = GetById + "/sections";
        public final static String Students = GetById + "/students";
        public final static String Teachers = GetById + "/teachers";
        public final static String Rooms = GetById + "/rooms";
        public final static String RoomsById = Rooms + "/{roomId}";

    }

    public static class Courses {
        public final static String Base = VersionPrefix + "courses";
        public final static String GetById = Base + "/{id}";
        public final static String Teachers = GetById + "/teachers";
    }
}
