from Course import Course
from Section import Section
from Teacher import Teacher
from Department import Department
import requests
import json
from typing import List
from datetime import datetime
import calendar

class Detector:

    def __init__(self):
        self.auth_header = self.get_token()

    def get_token(self):
        payload = {
            "username": "deviceId",
            "password": "password"
        }

        content_header = {'Content-Type': 'application/json'}
        url_deviceLogin = 'http://localhost:8080/v1/devices/login'
        response = requests.post(
            url_deviceLogin, headers=content_header, data=json.dumps(payload))
        sess = json.loads(response.text)['accessToken']
        return {'Authorization': sess}

    def get_teachers(self, courseId):
        urlTeacherCoursePair = 'http://localhost:8080/v1/courses/{}/teachers'.format(
            courseId)
        response = requests.get(urlTeacherCoursePair, headers=self.auth_header)
        for i in json.loads(response.text):
            teacher = Teacher(i['name'], i['sections'], i['courses'],
                                      list(i['department']), i['id'], i['type'])

    def get_courses(self):
        urlGetCourses = 'http://localhost:8080/v1/courses'
        response = requests.get(urlGetCourses, headers=self.auth_header)
        for i in json.loads(response.text):
            course = Course(**json.loads(json.dumps(i)))
            self.get_teachers(course.id)

    def get_sections(self):
        urlGetSection = 'http://localhost:8080/v1/sections/'
        response = requests.get(urlGetSection, headers=self.auth_header)
        for i in json.loads(response.text):
            section = Section(**json.loads(json.dumps(i)))
            self.get_section_schedule(section.id)
            self.get_section_students(section.id)

    def get_section_schedule(self,sectionId):
        urlGetSectionSchedule = 'http://localhost:8080/v1/sections/{}/timetable/'.format(sectionId)
        response = requests.get(urlGetSectionSchedule, headers=self.auth_header)
        print(json.loads(response.text))
        for k in json.loads(response.text).keys():
            for j in json.loads(response.text)[k]['classes'].keys():
                print(j + '->' + str(json.loads(response.text)[k]['classes'][j]))

    def get_section_students(self, sectionId):
        urlGetSectionStudents = 'http://localhost:8080/v1/sections/{}/students'.format(sectionId)
        response = requests.get(urlGetSectionStudents, headers = self.auth_header)
        for i in json.loads(response.text):
            self.get_section_courses(sectionId, i['id'])

    def get_section_courses(self, sectionId, studentId):
        urlSectionCourses = 'http://localhost:8080/v1/sections/{}/courses'.format(
            sectionId)
        response = requests.get(urlSectionCourses, headers=self.auth_header)
        print(json.loads(response.text))
        for i in json.loads(response.text):
            self.set_attendance(studentId, i)
    
    def set_attendance(self, studentId, courseId):
        urlSetAttendance = 'http://localhost:8080/v1/students/{}/courses/{}'.format(studentId, courseId)
        d = datetime.utcnow()
        unixtime = calendar.timegm(d.utctimetuple())
        self.auth_header['Content-Type'] = 'application/json'
        payload = {
            "time": [unixtime]
        }
        response = requests.patch(urlSetAttendance, data= payload, headers= self.auth_header)
        print(json.loads(response.text))

detector = Detector()
print(detector.auth_header)
detector.get_courses()
detector.get_sections()


# This should be the central controller
# Make a class here and make functions for the api calls
# Put each class in a file like Section.py should have section class, with a __str__ and a __dict__ is required i think not sure

# also include api call to sign in, you should'nt hard cord the token

# I did section.py, you can take a look otherwise this is good work


# first v1/devices/signin
# v1/devices - this will have sections inside
# for each section do
# v1/sections/sectionId/courses returns something like this
# {
#     "19CSE5IOT": {
#         "name": "Nitesh",
#         "sections": [
#             {
#                 "id": "5A-CSE-2019",
#                 "section": "A",
#                 "year": 2019,
#                 "semester": 5
#             }
#         ],
#         "courses": [
#             {
#                 "id": "19CSE5IOT",
#                 "name": "Internet of Things",
#                 "year": 2019,
#                 "semester": 5
#             }
#         ],
#         "department": {
#             "id": "cse",
#             "name": "Computer Science"
#         },
#         "id": "teacherId",
#         "type": "TEACHER"
#     }
# }
