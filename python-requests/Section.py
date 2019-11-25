class Section:
    def __init__(self, id: str, section: str, year: int, semester: int, room: int = None, daysScheduleMap: dict = None):
        self.id = id
        self.section = section
        self.year = year
        self.semester = semester
        self.room = room
        self.daysScheduleMap = daysScheduleMap

    def __str__(self):
        return "Section{}id={}, section={}, semester={}, year={}, room={}{}".format("{", self.id, self.section, self.semester, self.year, self.room, "}")
