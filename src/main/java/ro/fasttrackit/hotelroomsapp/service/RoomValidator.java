package ro.fasttrackit.hotelroomsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomsapp.exceptions.ValidationException;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;
import ro.fasttrackit.hotelroomsapp.repository.RoomRepository;

import java.util.Optional;

import static java.util.Optional.empty;

@Component
@RequiredArgsConstructor
public class RoomValidator {
    private final RoomRepository repository;

    public void validateNewThrow(Room newRoom) {
        validate(newRoom, true)
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    public void validateReplaceThrow(long roomId, Room newRoom) {
        exists(roomId)
                .or(() -> validate(newRoom, false))
                .ifPresent(ex -> {
                    throw ex;
                });
    }

    public void validateExistsOrThrow(long roomId) {
        exists(roomId).ifPresent(ex -> {
            throw ex;
        });
    }

    private Optional<ValidationException> validate(Room room, boolean newEntity) {
        if (room.getNumber() == null) {
            return Optional.of(new ValidationException("Number cannot be null"));
        } else if (room.getFloor() < 0) {
            return Optional.of(new ValidationException("City cannot be less than 0"));
        } else if (room.getHotelName() == null) {
            return Optional.of(new ValidationException("Hotel name cannot be null"));
        } else if (newEntity && repository.existsByHotelName(room.getHotelName() ) && repository.existsByNumber(room.getNumber())) {
            return Optional.of(new ValidationException("Number cannot be duplicate in the same Hotel"));
        } else if (!newEntity && repository.existsByHotelNameAndIdNot(room.getHotelName(), room.getId()) && repository.existsByNumberAndIdNot(room.getNumber(), room.getId())) {
            return Optional.of(new ValidationException("Number cannot be duplicate in the same Hotel"));
        } else {
            return empty();
        }
    }

    private Optional<ValidationException> exists(long roomId) {
        return repository.existsById(roomId)
                ? empty()
                : Optional.of(new ValidationException("Room with id " + roomId + " doesn't exist"));
    }
}
