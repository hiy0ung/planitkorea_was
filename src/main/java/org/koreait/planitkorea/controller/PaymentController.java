package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.pay.request.PaymentApproveRequestDto;
import org.koreait.planitkorea.dto.pay.request.PaymentRequestDto;
import org.koreait.planitkorea.dto.pay.response.ApproveResponseDto;
import org.koreait.planitkorea.dto.pay.response.PaymentResponseDto;
import org.koreait.planitkorea.service.KakaoPayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.KAKAO_PAY)
public class PaymentController {

    private final KakaoPayService kakaoPayService;

    private static final String POST_PAYMENT = "/request";
    private static final String PAYMENT_SUCCESS = "/success";
    private static final String PAYMENT_FAIL = "/fail";
    private static final String PAYMENT_CANCEL = "/cancel";

    // 결제 요청
    @PostMapping(POST_PAYMENT)
    public ResponseEntity<ResponseDto<PaymentResponseDto>> requestPayment(
            @RequestBody PaymentRequestDto dto
            ) {
        ResponseDto<PaymentResponseDto> response = kakaoPayService.requestPayment(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 결제 성공 시 리디렉션
    @PostMapping(PAYMENT_SUCCESS)
    public ResponseEntity<ResponseDto<ApproveResponseDto>> paymentSuccess(@RequestBody PaymentApproveRequestDto dto) {
        ResponseDto<ApproveResponseDto> response = kakaoPayService.approvePayment(dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 결제 실패 시 리디렉션
    @GetMapping(PAYMENT_FAIL)
    public ResponseEntity<String> paymentFail() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Failed");
    }

    // 결제 취소 시 리디렉션
    @GetMapping(PAYMENT_CANCEL)
    public ResponseEntity<String> paymentCancel() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Cancelled");
    }

}
