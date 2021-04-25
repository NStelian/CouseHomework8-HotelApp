package ro.fasttrackit.hotelroomsapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomsapp.exceptions.ResourceNotFoundException;
import ro.fasttrackit.hotelroomsapp.model.RoomsFilters;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;
import ro.fasttrackit.hotelroomsapp.repository.RoomDao;
import ro.fasttrackit.hotelroomsapp.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    private final RoomDao roomDao;
    private final RoomValidator validator;
    private final ObjectMapper mapper;

    public List<Room> getAll(RoomsFilters filters) {
        return roomDao.getAll(filters);
    }

    public Optional<Room> getRoomById(Long roomId) {
        return repository.findById(roomId);
    }

    public void deleteById(Long roomId) {
        repository.deleteById(roomId);
    }

    public Room replaceRoom(Room newRoom, Long roomId) {
        newRoom.setId(roomId);
        validator.validateReplaceThrow(roomId, newRoom);

        Room dbRoom = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find room with id " + roomId));
        copyRoom(newRoom, dbRoom);
        return repository.save(dbRoom);
    }

    private void copyRoom(Room newRoom, Room dbRoom) {
        dbRoom.setNumber(newRoom.getNumber());
        dbRoom.setFloor(newRoom.getFloor());
        dbRoom.setHotelName(newRoom.getHotelName());
    }

    @SneakyThrows
    public Room patchRoom(JsonPatch patch, long roomId) {
        validator.validateExistsOrThrow(roomId);
        Room dbRoom = repository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find room with id " + roomId));

        JsonNode patchedRoomJson = patch.apply(mapper.valueToTree(dbRoom));
        Room patchedRoom = mapper.treeToValue(patchedRoomJson, Room.class);
        return replaceRoom(patchedRoom, roomId);
    }
}
