import json, requests
from typing import List

class Section:
    id = None
    section = None
    year = None
    semester = None
    room = None

    def __init__(self, id, section, year, semester, room):
        self.id = id
        self.section = section
        self.year = semester
        self.room = room

class Courses:
    id = None
    name = None
    year = None
    semester = None

    def __init__(self, id, name, year, semester):
        self.id = id
        self.name = name
        self.year = year
        self.semester = semester

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

AuthHeader = { 'Authorization' : 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFjaGVySWQiLCJpYXQiOjE1NzQzMzM2NTQsImV4cCI6MTU3NDkzODQ1NH0.tvYFN_cBr9OyoqFR3T1ONM9ovI21O1IOPHGv4hK_AXSzxszq4mG3xCUx2X_JqXZPSQR1eZ6VB4paeR5_7ExgkQ'}
urlTeacherCoursePair = 'http://localhost:8080/v1/courses/19CSE5IOT/teachers'
response = requests.get(urlTeacherCoursePair, headers = AuthHeader)
for i in json.loads(response.text):
    teacher = Teacher(i['name'], i['sections'], i['courses'], list(i['department']), i['id'], i['type'])
    print('{}\n{}\n{}\n{}\n{}\n{}'.format(teacher.name, teacher.sections, teacher.courses, teacher.department, teacher.id, teacher.type))