package org.koreait.planitkorea.dto.Reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMyReservationResponseDto {
    private Long id;

    private Long userId;

    private Long productId;

    private Long subProductId;

    private Long person;

    private String totalPrice;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int reservationStatus;

    private String productImage;

    private String productName;
}
