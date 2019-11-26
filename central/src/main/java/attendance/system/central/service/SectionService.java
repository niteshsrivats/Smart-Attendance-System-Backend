package attendance.system.central.service;

import attendance.system.central.exceptions.BadRequestException;
import attendance.system.central.exceptions.DuplicateEntityException;
import attendance.system.central.exceptions.EntityNotFoundException;
import attendance.system.central.models.constants.Days;
import attendance.system.central.models.constants.Times;
import attendance.system.central.models.entities.*;
import attendance.system.central.repositories.postgres.CourseRepository;
import attendance.system.central.repositories.postgres.DepartmentRepository;
import attendance.system.central.repositories.postgres.SectionRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static attendance.system.central.utils.IdUtils.generateSectionId;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class SectionService {

    private final SectionRepository sectionRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public SectionService(SectionRepository sectionRepository, DepartmentRepository departmentRepository, CourseRepository courseRepository, TeacherService teacherService, StudentService studentService) {
        this.sectionRepository = sectionRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @Transactional
    public List<Section> getSections() {
        return this.sectionRepository.findAll();
    }

    @Transactional
    public Section getSectionById(String id) {
        if (id == null) {
            throw new BadRequestException("Section id cannot be null.");
        }
        return this.sectionRepository.findSectionById(id).orElseThrow(() -> new EntityNotFoundException(Section.class, id));
    }

    @Transactional
    public Set<Student> getSectionStudents(String sectionId) {
        Section section = getSectionById(sectionId);
        for (Student student : section.getStudents()) {
            Hibernate.initialize(student.getSections());
        }
        return section.getStudents();
    }

    @Transactional
    public Map<Course, String> getCourseTeacherMap(String sectionId) {
        Section section = getSectionById(sectionId);
        Hibernate.initialize(section.getCourseTeacherMap());
        HashMap<Course, String> courseTeacherIdMap = new HashMap<Course, String>();
        for (Course course : section.getCourseTeacherMap().keySet()) {
            courseTeacherIdMap.put(course, section.getCourseTeacherMap().get(course).getId());
        }
        return courseTeacherIdMap;
    }

    @Transactional
    public Map<Days, Schedule> getSectionTimetable(String sectionId) {
        Section section = getSectionById(sectionId);
        Hibernate.initialize(section.getDaysScheduleMap());
        return section.getDaysScheduleMap();
    }

    @Transactional
    public Section addSection(Section section) {
        try {
            section.setId(generateSectionId(section));
            getSectionById(section.getId());
            throw new DuplicateEntityException(Section.class, section.getId());
        } catch (EntityNotFoundException e) {
            section.setDepartment(departmentRepository.findDepartmentById(section.getDepartment().getId()).orElseThrow(
                    () -> new EntityNotFoundException(Department.class, section.getDepartment().getId())));
            return sectionRepository.save(section);
        }
    }

    @Transactional
    public Section addCourseTeacherPair(String id, String teacherId, Course course) {
        Section section = getSectionById(id);
        Course newCourse = courseRepository.findCourseById(course.getId()).orElseThrow(() -> new EntityNotFoundException(Course.class, course.getId()));
        Teacher teacher = teacherService.getTeacher(teacherId);
        section.getCourseTeacherMap().put(newCourse, teacher);
        Hibernate.initialize(section.getStudents());
        if (!teacher.getCourses().contains(newCourse)) {
            teacherService.addCourseToTeacher(teacherId, newCourse);
        }
        if (!teacher.getSections().contains(section)) {
            teacherService.addSectionToTeacher(teacherId, section);
        }
        for (Student student : section.getStudents()) {
            studentService.addStudentCourse(student.getId(), course);
        }
        return sectionRepository.save(section);
    }

    @Transactional
    public Map<Days, Schedule> addTimeTableSchedule(String id, Days day, Schedule schedule) {
        Section section = getSectionById(id);
        Hibernate.initialize(section.getDaysScheduleMap());
        for (Times time : schedule.getClasses().keySet()) {
            schedule.getClasses().put(
                    time,
                    courseRepository.findCourseById(schedule.getClasses().get(time).getId()).orElseThrow(
                            () -> new EntityNotFoundException(Course.class, schedule.getClasses().get(time).getId()))
            );
        }
        if (section.getDaysScheduleMap().get(day) == null) {
            section.getDaysScheduleMap().put(day, schedule);
        } else {
            section.getDaysScheduleMap().get(day).setClasses(schedule.getClasses());
        }
        return sectionRepository.save(section).getDaysScheduleMap();
    }
}
