package attendance.system.central.controller;

import attendance.system.central.models.entities.Room;
import attendance.system.central.models.entities.Section;
import attendance.system.central.named.Endpoints;
import attendance.system.central.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(Endpoints.Departments.RoomsById)
    public Room getRoom(@PathVariable @NotBlank String id) {
        return roomService.getRoomById(id);
    }

    @PostMapping(Endpoints.Departments.Rooms)
    public Room addRoom(@RequestBody @Valid @NotNull Room room) {
        return roomService.addRoom(room);
    }

    @PatchMapping(Endpoints.Departments.RoomsById)
    public Room updateSection(
            @PathVariable @NotBlank String id,
            @RequestBody @Valid @NotNull Section section) {
        return roomService.updateSection(id, section);
    }
}
