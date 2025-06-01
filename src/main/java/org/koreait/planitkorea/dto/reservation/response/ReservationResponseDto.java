package org.koreait.planitkorea.dto.reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.Reservation;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {
<<<<<<< HEAD
    private Long reservationId;
=======
    private Long id;
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
    private Long userId;
    private Long productId;
    private Long subProductId;
    private Long person;
    private String totalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
<<<<<<< HEAD
    private String orderId;
    private int reservationStatus;

    public ReservationResponseDto(Reservation reservation) {
        this.reservationId = reservation.getId();
=======
    private int reservationStatus;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
        this.userId = reservation.getUser().getId();
        this.productId = reservation.getProduct().getId();
        this.subProductId = reservation.getSubProduct().getId();
        this.person = reservation.getPerson();
        this.totalPrice = reservation.getTotalPrice();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
<<<<<<< HEAD
        this.orderId = reservation.getOrderId();
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
        this.reservationStatus = reservation.getReservationStatus();
    }
}
