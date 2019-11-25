package attendance.system.central.controller;

import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Student;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class SectionController {
    private final SectionService sectionService;

    @Autowired
    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(Endpoints.Sections.Base)
    public List<Section> getSections() {
        return sectionService.getSections();
    }

    @GetMapping(Endpoints.Sections.GetById)
    public Section getSectionById(@PathVariable @NotBlank String id) {
        return sectionService.getSectionById(id);
    }

    @GetMapping(Endpoints.Sections.Students)
    public Set<Student> getSectionStudents(@PathVariable @NotBlank String id) {
        return sectionService.getSectionStudents(id);
    }

    @GetMapping(Endpoints.Sections.Courses)
    public Map<Course, String> getSectionCourses(@PathVariable @NotBlank String id) {
        return sectionService.getCourseTeacherMap(id);
    }

    @PostMapping(Endpoints.Sections.Base)
    public Section addSection(@RequestBody @Valid @NotNull Section section) {
        return sectionService.addSection(section);
    }

    @PatchMapping(Endpoints.Sections.CourseTeacherPair)
    public Section addSection(
            @PathVariable @NotBlank String id,
            @PathVariable @NotBlank String teacherId,
            @RequestBody @Valid @NotNull Course course) {
        return sectionService.addCourseTeacherPair(id, teacherId, course);
    }
}
