package attendance.system.central.service;

import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.UserType;
import attendance.system.central.models.entities.Student;
import attendance.system.central.repositories.postgres.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class StudentService {

    private final AuthorizationEntityService authorizationEntityService;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(AuthorizationEntityService authorizationEntityService, StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.authorizationEntityService = authorizationEntityService;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findStudentByEntity_Id(id).orElseThrow(() -> new EntityNotFoundException(Student.class, id));
    }

    public Student addStudent(Student student) {
        if (authorizationEntityService.entityExists(student.getEntity().getId())) {
            throw new DuplicateEntityException(Student.class, student.getEntity().getId());
        }
        student.getEntity().setPassword(passwordEncoder.encode(student.getEntity().getPassword()));
        student.getEntity().setUserType(UserType.STUDENT);
        return studentRepository.save(student);
    }
}
