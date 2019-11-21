package attendance.system.central.controller;

import attendance.system.central.models.entities.*;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(Endpoints.Departments.Base)
    public List<Department> getDepartment() {
        return departmentService.getDepartments();
    }

    @GetMapping(Endpoints.Departments.GetById)
    public Department getDepartment(@PathVariable @NotBlank String id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping(Endpoints.Departments.Courses)
    public List<Course> getDepartmentCourses(@PathVariable @NotBlank String id) {
        return departmentService.getDepartmentCourses(id);
    }

    @GetMapping(Endpoints.Departments.Sections)
    public Collection<Section> getDepartmentSections(@PathVariable @NotBlank String id,
                                                     @RequestParam(required = false) Integer year) {
        return departmentService.getDepartmentSections(id, year);
    }

    @GetMapping(Endpoints.Departments.Students)
    public List<Student> getDepartmentStudents(@PathVariable @NotBlank String id,
                                               @RequestParam(required = false) Integer year) {
        return departmentService.getDepartmentStudents(id, year);
    }

    @GetMapping(Endpoints.Departments.Teachers)
    public List<Teacher> getDepartmentTeachers(@PathVariable @NotBlank String id) {
        return departmentService.getDepartmentTeachers(id);
    }

    @GetMapping(Endpoints.Departments.Rooms)
    public List<Room> getDepartmentRooms(@PathVariable @NotBlank String id) {
        return departmentService.getDepartmentRooms(id);
    }

    @PostMapping(Endpoints.Departments.Base)
    public Department addDepartment(@RequestBody @Valid @NotNull Department department) {
        return departmentService.addDepartment(department);
    }
}
