package attendance.system.central.controller;

import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
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

    @GetMapping(Endpoints.Sections.Teachers)
    public Set<Teacher> getSectionTeachers(@PathVariable @NotBlank String id) {
        return sectionService.getSectionTeachers(id);
    }

    @GetMapping(Endpoints.Sections.TeacherById)
    public Teacher getTeacherSectionById(@PathVariable @NotBlank String id,
                                         @PathVariable @NotBlank String teacherId) {
        return sectionService.getSectionTeacherById(id, teacherId);
    }

    @PostMapping(Endpoints.Sections.Base)
    public Section addSection(@RequestBody @Valid @NotNull Section section) {
        return sectionService.addSection(section);
    }
}
