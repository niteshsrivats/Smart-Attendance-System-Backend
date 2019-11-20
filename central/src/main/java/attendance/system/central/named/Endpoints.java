package attendance.system.central.named;

import static attendance.system.central.named.Placeholders.VersionPrefix;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class Endpoints {

    public static class Students {
        public final static String Base = VersionPrefix + "students";
        public final static String GetById = VersionPrefix + "students/{id}";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/signin";
    }

    public final static String Courses = VersionPrefix + "courses";

    public final static String Departments = VersionPrefix + "departments";

    public final static String Semesters = VersionPrefix + "semesters";
}
