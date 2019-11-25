from typing import List
from Section import Section
from Course import Course
from Department import Department


class Teacher:

    def __init__(self, name, section: List[Section], courses: List[Course], department: List[Department], id, type):
        self.name = name
        self.courses = courses
        self.sections = section
        self.department = department
        self.id = id
        self.type = type

    def __str__(self):
        return 'Teacher{}name={}, section={}, courses= {}, department= {}, id= {}, type= {}{}'.format('{', self.name, self.sections, self.courses, self.department, self.id, self.type)
