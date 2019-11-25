class Course:
    id = None
    name = None
    year = None
    semester = None

    def __init__(self, id: str, name: str, year: int, semester: int):
        self.id = id
        self.name = name
        self.year = year
        self.semester = semester

    def __str__(self):
        return 'Course{} id={}, name={}, year={}, semester={}{}'.format('{', self.id, self.name, self.year, self.semester, '}')
