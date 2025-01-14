package org.koreait.planitkorea.dto.Reservation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReservationRequestDto {
    @NotBlank
    private Long productId;

    @NotBlank
    private Long subProductId;

    @NotBlank
    private Long person;

    @NotBlank
    private String totalPrice;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
