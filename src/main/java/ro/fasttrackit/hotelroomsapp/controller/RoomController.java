package ro.fasttrackit.hotelroomsapp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.hotelroomsapp.exceptions.ResourceNotFoundException;
import ro.fasttrackit.hotelroomsapp.model.RoomsFilters;
import ro.fasttrackit.hotelroomsapp.model.entity.Cleanup;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;
import ro.fasttrackit.hotelroomsapp.service.CleanupService;
import ro.fasttrackit.hotelroomsapp.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final CleanupService cleanupService;


    @GetMapping("rooms")
    List<Room> getAll(RoomsFilters filters) {
        return roomService.getAll(filters);
    }

    @GetMapping("{roomId}")
    Room getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room with id " + roomId + " not found"));
    }

    @DeleteMapping("{roomId}")
    void deleteById(@PathVariable Long roomId) {
        roomService.deleteById(roomId);
    }

    @GetMapping(path = "/cleanup/room/{roomId}")
    List<Cleanup> getCleanups(@PathVariable Long roomId) {
        return cleanupService.getCleanupsByRoomId(roomId);
    }

    @PatchMapping("{roomId}")
    Room patchRoom(@RequestBody JsonPatch patch, @PathVariable Long roomId) {
        return roomService.patchRoom(patch, roomId);
    }
}
