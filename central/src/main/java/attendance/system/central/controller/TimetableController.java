package attendance.system.central.controller;

import attendance.system.central.models.constants.Days;
import attendance.system.central.models.entities.Schedule;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class TimetableController {
    private final SectionService sectionService;

    @Autowired
    public TimetableController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping(Endpoints.Sections.Timetable)
    public Map<Days, Schedule> getTimeTable(@PathVariable @NotBlank String id) {
        return sectionService.getSectionTimetable(id);
    }

    @PatchMapping(Endpoints.Sections.TimetableSchedule)
    public Map<Days, Schedule> addTimeTableSchedule(
            @PathVariable @NotBlank String id,
            @PathVariable @NotBlank Days day,
            @RequestBody @Valid @NotNull Schedule schedule) {
        return sectionService.addTimeTableSchedule(id, day, schedule);
    }
}
