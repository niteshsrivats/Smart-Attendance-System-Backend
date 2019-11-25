import Course
import Section
import Teacher
import requests
import json
from typing import List


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
        sess = Detector(json.loads(response.text)['accessToken'])
        return {'Authorization': sess.token}

    def get_courses(self):
        urlGetCourses = 'http://localhost:8080/v1/courses'
        response = requests.get(urlGetCourses, headers=self.auth_header)
        for i in json.loads(response.text):
            course = Course.Courses(**json.loads(json.dumps(i)))
            get_teachers(course.id)

    def get_sections(self):
        urlGetSection = 'http://localhost:8080/v1/sections/'
        response = requests.get(urlGetSection, headers=self.auth_header)
        for i in json.loads(response.text):
            section = Section.Section(**json.loads(json.dumps(i)))
            get_section_courses(section.id)

    def get_teachers(self, courseId):
        urlTeacherCoursePair = 'http://localhost:8080/v1/courses/{}/teachers'.format(
            courseId)
        response = requests.get(urlTeacherCoursePair, headers=self.auth_header)
        for i in json.loads(response.text):
            teacher = Teacher.Teacher(i['name'], i['sections'], i['courses'],
                                      list(i['department']), i['id'], i['type'])

    def get_section_courses(self, sectionId):
        urlSectionCourses = 'http://localhost:8080/v1/sections/{}/courses'.format(
            sectionId)
        response = requests.get(urlSectionCourses, headers=self.auth_header)
        print(json.loads(response.text))
        for i in json.loads(response.text):
            print(i)


detector = Detector()
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
