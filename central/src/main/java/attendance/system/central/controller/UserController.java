package attendance.system.central.controller;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.models.constants.EntityType;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.UserPrincipal;
import attendance.system.central.service.StudentService;
import attendance.system.central.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class UserController {
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Autowired
    public UserController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @GetMapping(Endpoints.Users.Base)
    public Object getUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        if (userPrincipal.getAuthorizationEntity().getType() == EntityType.STUDENT) {
            return studentService.getStudent(userPrincipal.getUsername());
        } else if (userPrincipal.getAuthorizationEntity().getType() == EntityType.TEACHER) {
            return teacherService.getTeacher(userPrincipal.getUsername());
        } else {
            throw new BadRequestException("Unknown entity type.");
        }
    }
}
