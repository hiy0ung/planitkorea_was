package org.koreait.planitkorea.dto.reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.planitkorea.entity.Reservation;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderIdByReservationDto {
    private Long reservationId;
    private Long userId;
    private Long productId;
    private Long subProductId;
    private Long person;
    private String totalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderId;
    private int reservationStatus;

    public GetOrderIdByReservationDto(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.userId = reservation.getUser().getId();
        this.productId = reservation.getProduct().getId();
        this.subProductId = reservation.getSubProduct().getId();
        this.person = reservation.getPerson();
        this.totalPrice = reservation.getTotalPrice();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.orderId = reservation.getOrderId();
        this.reservationStatus = reservation.getReservationStatus();
    }
}
