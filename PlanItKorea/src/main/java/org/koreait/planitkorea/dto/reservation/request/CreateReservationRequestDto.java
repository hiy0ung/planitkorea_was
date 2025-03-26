package org.koreait.planitkorea.dto.reservation.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationRequestDto {
    private String cid;
    private String partner_order_id;
    private String partner_user_id;
    private String item_name;
    private String item_code;
    private int quantity;
    private String total_amount;
    private int tax_free_amount;
    private String approval_url;
    private String fail_url;
    private String cancel_url;

    @NotBlank
    private Long productId;

    @NotBlank
    private Long subProductId;

    @NotBlank
    private Long person;

    @NotBlank
    private String totalPrice;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private String orderId;
}

