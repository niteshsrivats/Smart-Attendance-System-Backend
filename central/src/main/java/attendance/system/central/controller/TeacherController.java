package attendance.system.central.controller;

import attendance.system.central.models.entities.Section;
import attendance.system.central.models.entities.Teacher;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @GetMapping(Endpoints.Teachers.GetById)
    public Teacher getTeacherById(@PathVariable @NotBlank String id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping(Endpoints.Teachers.Sections)
    public Set<Section> getTeacherSections(@PathVariable @NotBlank String id) {
        return teacherService.getTeacherSections(id);
    }

    @GetMapping(Endpoints.Teachers.SectionById)
    public Section getTeacherSectionById(@PathVariable @NotBlank String id,
                                         @PathVariable @NotBlank String sectionId) {
        return teacherService.getTeacherSectionById(id, sectionId);
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

    @PatchMapping(Endpoints.Teachers.SectionById)
    public Teacher addSectionToTeacher(@PathVariable @NotBlank String id,
                                       @PathVariable @NotBlank String sectionId) {
        return teacherService.addSectionToTeacher(id, sectionId);
    }
}
