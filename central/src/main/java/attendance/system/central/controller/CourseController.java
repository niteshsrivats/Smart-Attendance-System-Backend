package attendance.system.central.controller;

import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */


@RestController
@CrossOrigin
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(Endpoints.Courses.Base)
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping(Endpoints.Courses.GetById)
    public Course getCourse(@PathVariable @NotBlank String id) {
        return courseService.getCourseById(id);
    }

    @GetMapping(Endpoints.Courses.Teachers)
    public List<Teacher> getDepartmentCourses(@PathVariable @NotBlank String id) {
        return courseService.getCourseTeachers(id);
    }

    @PostMapping(Endpoints.Courses.Base)
    public Course addCourse(@RequestBody @Valid @NotNull Course course) {
        return courseService.addCourse(course);
    }

    @DeleteMapping(Endpoints.Courses.GetById)
    public void deleteCourse(@PathVariable @NotBlank String id) {
        courseService.deleteCourse(id);
    }
}
