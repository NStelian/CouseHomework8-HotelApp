package ro.fasttrackit.hotelroomsapp;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.fasttrackit.hotelroomsapp.model.entity.Cleanup;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;
import ro.fasttrackit.hotelroomsapp.model.entity.RoomFacilities;
import ro.fasttrackit.hotelroomsapp.repository.CleanupRepository;
import ro.fasttrackit.hotelroomsapp.repository.RoomFacilitiesRepository;
import ro.fasttrackit.hotelroomsapp.repository.RoomRepository;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final CleanupRepository cleanupRepository;
    private final RoomFacilitiesRepository roomFacilitiesRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Room> rooms = roomRepository.saveAll(List.of(
                Room.builder()
                        .number("123")
                        .floor(1)
                        .hotelName("Astoria")
                        .build(),
                Room.builder()
                        .number("212")
                        .floor(2)
                        .hotelName("Hilton")
                        .build()
        ));

        List<Cleanup> cleanups = cleanupRepository.saveAll(List.of(
                Cleanup.builder()
                        .date(LocalDate.now())
                        .room(rooms.get(0))
                        .build()
        ));

        List<RoomFacilities> roomFacilities = roomFacilitiesRepository.saveAll(List.of(
                RoomFacilities.builder()
                        .hasDoubledBed(true)
                        .hasTv(true)
                        .room(rooms.get(0))
                        .build(),
                RoomFacilities.builder()
                        .hasDoubledBed(false)
                        .hasTv(false)
                        .room(rooms.get(1))
                        .build()
        ));
    }
}
