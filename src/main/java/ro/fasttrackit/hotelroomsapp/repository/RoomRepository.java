package ro.fasttrackit.hotelroomsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByHotelName(String hotelName);
    boolean existsByNumber(String number);
    boolean existsByHotelNameAndIdNot(String hotelName, long id);
    boolean existsByNumberAndIdNot(String number, long id);
}
