package ro.fasttrackit.hotelroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomsapp.model.entity.Cleanup;

import java.util.List;

@Repository
public interface CleanupRepository extends JpaRepository<Cleanup, Long> {
    List<Cleanup> findCleanupsByRoomId(Long roomId);
}
