package attendance.system.central.controller;

import attendance.system.central.models.entities.Attendance;
import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Student;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.security.UserPrincipal;
import attendance.system.central.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class StudentController {

    private final StudentService studentService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public StudentController(StudentService studentService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.studentService = studentService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(Endpoints.Students.GetById)
    public Student getStudent(@PathVariable @NotBlank String id) {
        return studentService.getStudent(id);
    }

    @GetMapping(Endpoints.Students.Sections)
    public Set<Section> getStudentSections(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return studentService.getStudentSections(userPrincipal.getUsername());
    }

    @GetMapping(Endpoints.Students.GetCourseAttendance)
    public Map<Course, Attendance> getStudentAttendance(@PathVariable @NotBlank String id) {
        return studentService.getStudentAttendance(id);
    }

    @PostMapping(Endpoints.Students.Signup)
    public Student registerStudent(@RequestBody @Valid @NotNull Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping(Endpoints.Students.Signin)
    public JwtAuthenticationResponse authenticateStudent(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    @PatchMapping(Endpoints.Students.Sections)
    public Student addStudentSection(@PathVariable String id,
                                     @RequestBody @Valid @NotNull Section section) {
        return studentService.addSectionToStudent(id, section);
    }

    @PatchMapping(Endpoints.Students.CourseAttendancePresent)
    public Map<Course, Attendance> addStudentAttendancePresent(
            @PathVariable @NotBlank String id,
            @PathVariable @NotBlank String courseId) {
        return studentService.addStudentAttendance(id, courseId, true);
    }

    @PatchMapping(Endpoints.Students.CourseAttendanceAbsent)
    public Map<Course, Attendance> addStudentAttendanceAbsent(
            @PathVariable @NotBlank String id,
            @PathVariable @NotBlank String courseId) {
        return studentService.addStudentAttendance(id, courseId, false);
    }
}
