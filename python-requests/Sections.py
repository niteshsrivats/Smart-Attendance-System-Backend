import json
import requests


class Section:
    id = None
    section = None
    year = None
    semester = None
    room = None

    # def __str__(self):
    #     return ""

    def __init__(self, id: str, section: str, year: int, semester: int, room=None):
        self.id = id
        self.section = section
        self.year = year
        self.semester = semester
        self.room = room


AuthHeader = {'Authorization': 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFjaGVySWQiLCJpYXQiOjE1NzQ2NjI4NzEsImV4cCI6MTU3NTI2NzY3MX0.WGooZDn9RV9UXVH9h0eeZz-TVTMSH_RU2TmJ6Zh8Ji63lslP_k5sveYlMYgvEr921PXDRoZASnkuFpTm9A-Hsw'}
urlGetSection = 'http://localhost:8080/v1/sections/'
response = requests.get(urlGetSection, headers=AuthHeader)
for i in json.loads(response.text):
    print(i)
    section = Section(**json.loads(json.dumps(i)))
    print('Section ID: {}\nSection: {}\nYear: {}\nSem: {}\nRoom: {}\n'.format(
        section.id, section.section, section.year, section.semester, section.room))
