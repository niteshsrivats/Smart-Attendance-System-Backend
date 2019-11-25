from typing import List
import Section, Course

class Department:
    id = None
    name = None

    def __init__(self, id, name):
        self.id = id
        self.name = name
        
class Teacher:

    def __init__(self, name, section: List[Section], courses: List[Courses], department: List[Department], id, type):
        self.name = name
        self.courses = courses
        self.sections = section
        self.department = department
        self.id = id
        self.type = type
    
    def __str__(self):
        return 'Teacher{}name={}, section={}, courses= {}, department= {}, id= {}, type= {}{}'.format('{', self.name, self.sections, self.courses, self.department, self.id, self.type)
