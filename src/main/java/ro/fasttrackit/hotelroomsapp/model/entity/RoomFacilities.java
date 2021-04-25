package ro.fasttrackit.hotelroomsapp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomFacilities {
    @Id
    @GeneratedValue
    private Long id;

    private boolean hasTv;
    private boolean hasDoubledBed;

    @OneToOne
    private Room room;
}
