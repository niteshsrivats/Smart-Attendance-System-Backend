package attendance.system.central.controller;

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

    @RequestMapping(Endpoints.Teachers.Base)
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    @RequestMapping(Endpoints.Teachers.GetById)
    @GetMapping
    public Teacher getTeacherById(@PathVariable @NotBlank String id) {
        return teacherService.getTeacherById(id);
    }

    @RequestMapping(Endpoints.Teachers.Sections)
    @GetMapping
    public List<Section> getTeacherSections(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return teacherService.getTeacherSections(userPrincipal.getUsername());
    }

    @RequestMapping(Endpoints.Teachers.SectionById)
    @GetMapping
    public Section getTeacherSectionById(@PathVariable @NotBlank String id,
                                         @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return teacherService.getTeacherSectionById(userPrincipal.getUsername(), id);
    }

    @RequestMapping(Endpoints.Teachers.Signup)
    @PostMapping
    public Teacher registerTeacher(@RequestBody @Valid @NotNull Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping(Endpoints.Teachers.Signin)
    @PostMapping
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
}
