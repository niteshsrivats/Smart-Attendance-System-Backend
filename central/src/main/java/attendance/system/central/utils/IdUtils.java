package attendance.system.central.utils;

import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Section;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

public class IdUtils {

    public static String generateSectionId(Section section) {
        return section.getSemester().toString() + section.getSection() + section.getDepartment().getId().toUpperCase() + "-" + section.getYear();

    }

    public static String generateCourseId(Course course) {
        String courseName = course.getName().trim();
        StringBuilder abbreviation = new StringBuilder(courseName.substring(0, 1).toUpperCase());
        for(int i = 1; i < course.getName().length() - 1; i++) {
            if (courseName.substring(i, i + 1).equals(" ")) {
                abbreviation.append(courseName.substring(i + 1, i + 2).toUpperCase());
            }
        }
        return ((course.getYear() % 100) + course.getDepartment().getId().toUpperCase() + course.getSemester() + abbreviation);
    }
}
