package org.koreait.planitkorea.repository;

import jakarta.transaction.Transactional;
import org.koreait.planitkorea.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r, pi.productImage, p.productName FROM Reservation r JOIN ProductImage pi ON " +
            "r.productId = pi.productId JOIN Product p ON r.productId = p.id WHERE r.userId = :userId")
    List<Object[]> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.reservationStatus = 2 WHERE r.reservationStatus = 1 AND r.endDate < CURRENT_TIMESTAMP")
    void updateExpiredReservations();
}
