package attendance.system.central.controller;

import attendance.system.central.models.entities.Student;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.service.StudentService;
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

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
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

    @RequestMapping(Endpoints.Students.Base)
    @GetMapping
    public List<Student> getUsers() {
        return studentService.getStudents();
    }

    @RequestMapping(Endpoints.Students.GetById)
    @GetMapping
    public Student getStudentById(@PathVariable @NotBlank String id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping(Endpoints.Students.Signup)
    @PostMapping
    public Student registerStudent(@RequestBody @Valid @NotNull Student student) {
        return studentService.addStudent(student);
    }

    @RequestMapping(Endpoints.Students.Signin)
    @PostMapping
    public JwtAuthenticationResponse authenticateUser(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
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
