import json
import requests


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


AuthHeader = {'Authorization': 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFjaGVySWQiLCJpYXQiOjE1NzQzMzM2NTQsImV4cCI6MTU3NDkzODQ1NH0.tvYFN_cBr9OyoqFR3T1ONM9ovI21O1IOPHGv4hK_AXSzxszq4mG3xCUx2X_JqXZPSQR1eZ6VB4paeR5_7ExgkQ'}
urlGetCourses = 'http://localhost:8080/v1/courses'
response = requests.get(urlGetCourses, headers=AuthHeader)
for i in json.loads(response.text):
    course = Courses(**json.loads(json.dumps(i)))
    print('Course ID: {}\nCourse Name: {}\nCourse Year: {}\nCourse Semester: {}\n'.format(
        course.id, course.name, course.year, course.semester))
