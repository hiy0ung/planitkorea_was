package org.koreait.planitkorea.dto.reservation.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMyReservationResponseDto {
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

    private LocalDateTime startDate;

    private LocalDateTime endDate;

<<<<<<< HEAD
    private String orderId;

=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
    private int reservationStatus;

    private String productImage;

    private String productName;
}
