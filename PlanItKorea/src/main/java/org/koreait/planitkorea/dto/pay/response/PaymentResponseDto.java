package org.koreait.planitkorea.dto.pay.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {
    private String tid;
    private String next_redirect_pc_url;
}
