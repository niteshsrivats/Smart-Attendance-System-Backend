package attendance.system.central.controller;

import attendance.system.central.models.entities.Course;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.security.UserPrincipal;
import attendance.system.central.service.TeacherService;
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
import java.util.List;
import java.util.Set;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
public class TeacherController {
    private final TeacherService teacherService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public TeacherController(TeacherService teacherService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.teacherService = teacherService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(Endpoints.Teachers.Base)
    public Teacher getTeacher(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return teacherService.getTeacher(userPrincipal.getUsername());
    }

    @GetMapping(Endpoints.Teachers.Sections)
    public List<Section> getTeacherSections(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return teacherService.getTeacherSections(userPrincipal.getUsername());
    }

    @GetMapping(Endpoints.Teachers.Courses)
    public Set<Course> getTeacherCourses(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return teacherService.getTeacherCourses(userPrincipal.getUsername());
    }

    @PostMapping(Endpoints.Teachers.Signup)
    public Teacher registerTeacher(@RequestBody @Valid @NotNull Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @PostMapping(Endpoints.Teachers.Signin)
    public JwtAuthenticationResponse authenticateTeacher(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    @PatchMapping(Endpoints.Teachers.ExplicitIdCourses)
    public Teacher addCourseToTeacher(@PathVariable @NotBlank String id,
                                      @RequestBody @Valid @NotNull Course course) {
        return teacherService.addCourseToTeacher(id, course);
    }

    @PatchMapping(Endpoints.Teachers.ExplicitIdSections)
    public Teacher addSectionToTeacher(@PathVariable @NotBlank String id,
                                       @RequestBody @Valid @NotNull Section section) {
        return teacherService.addSectionToTeacher(id, section);
    }

    @DeleteMapping(Endpoints.Teachers.GetById)
    public void deleteTeacher(@PathVariable @NotBlank String id) {
        teacherService.deleteTeacher(id);
    }
}
