package ro.fasttrackit.hotelroomsapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.hotelroomsapp.model.entity.Cleanup;
import ro.fasttrackit.hotelroomsapp.repository.CleanupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CleanupService {
    private final CleanupRepository cleanupRepository;

    public List<Cleanup> getCleanupsByRoomId(Long roomId) {
        return cleanupRepository.findCleanupsByRoomId(roomId);
    }
}
