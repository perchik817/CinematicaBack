package megalab.cinematica.dao;

import feign.Param;
import megalab.cinematica.base.BaseRep;
import megalab.cinematica.models.entity.Seats;
import megalab.cinematica.models.enums.SeatsStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRep extends BaseRep<Seats> {
    @Query("SELECT s FROM Seats s WHERE s.hall.id = :hallId AND s.seat = :seat")
    Seats findSeatByHallAndSeat(@Param("hallId") Long hallId, @Param("seat") int seat);
    @Query("SELECT s.seat FROM Seats s WHERE s.hall.id = :hallId AND s.status = :status")
    List<Integer> findOccupiedSeatsByHallIdAndStatus(@Param("hallId") Long hallId, @Param("status") SeatsStatus status);
}
