package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.pay.request.PaymentApproveRequestDto;
import org.koreait.planitkorea.dto.pay.request.PaymentRequestDto;
import org.koreait.planitkorea.dto.pay.response.PaymentResponseDto;

public interface KakaoPayService {
    ResponseDto<PaymentResponseDto> requestPayment(PaymentRequestDto dto);

    ResponseDto<String> approvePayment(PaymentApproveRequestDto dto);
}
