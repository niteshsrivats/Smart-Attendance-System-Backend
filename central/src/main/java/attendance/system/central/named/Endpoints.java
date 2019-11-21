package attendance.system.central.named;

import static attendance.system.central.named.Placeholders.VersionPrefix;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class Endpoints {

    public final static String Courses = VersionPrefix + "courses";
    public final static String Semesters = VersionPrefix + "semesters";

    public static class Students {
        public final static String Base = VersionPrefix + "students";
        public final static String GetById = Base + "/{id}";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/signin";

        public final static String Sections = Base + "/sections";
    }

    public static class Teachers {
        public final static String Base = VersionPrefix + "teachers";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/signin";

        public final static String Sections = Base + "/sections";
        public final static String Courses = Base + "/courses";
    }

    public static class Devices {
        public final static String Base = VersionPrefix + "devices";
        public final static String Signup = Base + "/signup";
        public final static String Signin = Base + "/signin";

        public final static String Sections = Base + "/sections";
    }

    public static class Sections {
        public final static String Base = VersionPrefix + "sections";
        public final static String GetById = Base + "/{id}";
        public final static String Teachers = GetById + "/teachers";
    }

    public static class Departments {
        public final static String Base = VersionPrefix + "departments";
        public final static String GetById = Base + "/{id}";
        public final static String Courses = GetById + "/courses";
        public final static String Sections = GetById + "/sections";
        public final static String Students = GetById + "/students";
        public final static String Teachers = GetById + "/teachers";
    }
}
