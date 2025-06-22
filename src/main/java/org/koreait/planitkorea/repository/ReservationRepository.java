package org.koreait.planitkorea.repository;

import jakarta.transaction.Transactional;
import org.koreait.planitkorea.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = """
        SELECT r.*,
               MIN(pi.product_image) AS product_image,
               p.product_name
        FROM reservations r
        JOIN products p ON r.product_id = p.id
        JOIN product_images pi ON p.id = pi.product_id
        WHERE r.user_id = :userId
        GROUP BY r.id, p.product_name
    """, nativeQuery = true)
    List<Object[]> findAllByUserId(@Param("userId") Long userId);


    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.reservationStatus = 2 WHERE r.reservationStatus = 1 AND r.endDate < current_date")
    void updateExpiredReservations();

    Optional<Reservation> findByOrderId(String orderId);
}

