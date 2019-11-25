import json
import requests

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
    
AuthHeader = { 'Authorization' : 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWFjaGVySWQiLCJpYXQiOjE1NzQzMzM2NTQsImV4cCI6MTU3NDkzODQ1NH0.tvYFN_cBr9OyoqFR3T1ONM9ovI21O1IOPHGv4hK_AXSzxszq4mG3xCUx2X_JqXZPSQR1eZ6VB4paeR5_7ExgkQ'}
urlGetSection = 'http://localhost:8080/v1/sections/'
response = requests.get(urlGetSection, headers = AuthHeader)
for i in json.loads(response.text):
    section = Section(**json.loads(json.dumps(i)))
    print('Section ID: {}\nSection: {}\nYear: {}\nSem: {}\nRoom: {}\n'.format(section.id,section.section,section.year,section.semester,section.room))
