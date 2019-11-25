class Section:
    def __init__(self, id: str, section: str, year: int, semester: int, room: int = None):
        self.id = id
        self.section = section
        self.year = year
        self.semester = semester
        self.room = room

    def __str__(self):
        return "Section{}id={}, section={}, semester={}, year={}, room={}{}".format("{", self.id, self.section, self.semester, self.year, self.room, "}")


a = Section("1", "A", 1, 1)
print(a)
