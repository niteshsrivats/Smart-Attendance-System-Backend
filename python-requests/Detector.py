

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
