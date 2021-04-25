package ro.fasttrackit.hotelroomsapp.repository;

import org.springframework.stereotype.Repository;
import ro.fasttrackit.hotelroomsapp.model.RoomsFilters;
import ro.fasttrackit.hotelroomsapp.model.entity.Room;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class RoomDao {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomDao(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    public List<Room> getAll(RoomsFilters filters) {
        CriteriaQuery<Room> criteria = criteriaBuilder.createQuery(Room.class);
        Root<Room> root = criteria.from(Room.class);

        List<Predicate> whereClause = new ArrayList<>();
        ofNullable(filters.getNumber())
                .ifPresent(number -> whereClause.add(criteriaBuilder.equal(root.get("number"), number)));
        ofNullable(filters.getFloor())
                .ifPresent(floor -> whereClause.add(criteriaBuilder.equal(root.get("floor"), floor)));
        ofNullable(filters.getHotel())
                .ifPresent(hotel -> whereClause.add(criteriaBuilder.equal(root.get("hotelName"), hotel)));

        CriteriaQuery<Room> query = criteria.select(root).where(whereClause.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
