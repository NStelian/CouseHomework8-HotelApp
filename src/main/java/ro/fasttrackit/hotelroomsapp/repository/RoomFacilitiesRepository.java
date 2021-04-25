package ro.fasttrackit.hotelroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomsapp.model.entity.RoomFacilities;

@Repository
public interface RoomFacilitiesRepository extends JpaRepository<RoomFacilities, Long> {
}
