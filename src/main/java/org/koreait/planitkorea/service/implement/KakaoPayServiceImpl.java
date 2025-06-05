package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.pay.request.PaymentApproveRequestDto;
import org.koreait.planitkorea.dto.pay.request.PaymentRequestDto;
import org.koreait.planitkorea.dto.pay.response.ApproveResponseDto;
import org.koreait.planitkorea.dto.pay.response.PaymentResponseDto;
import org.koreait.planitkorea.entity.Payment;
import org.koreait.planitkorea.repository.PaymentRepository;
import org.koreait.planitkorea.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoPayServiceImpl implements KakaoPayService {

    private final PaymentRepository paymentRepository;
    private final ReservationServiceImpl reservationService;

    @Value("${kakao.pay.client-id}")
    private String clientId;

    @Value("${kakao.pay.secret-key}")
    private String secretKey;

    private final String KAKAO_PAY_API_URL = "https://open-api.kakaopay.com/online/v1/payment/ready"; // 결제 요청 URL

    @Override
    public ResponseDto<PaymentResponseDto> requestPayment(PaymentRequestDto dto) {
        try {
            // 요청 파라미터 설정
            Map<String, Object> params = new HashMap<>();
            params.put("cid", clientId);
            params.put("partner_order_id", dto.getPartner_order_id());
            params.put("partner_user_id", dto.getPartner_user_id());
            params.put("item_name", dto.getItem_name());
            params.put("item_code", dto.getItem_code());
            params.put("quantity", dto.getQuantity());
            params.put("total_amount", dto.getTotal_amount());
            params.put("tax_free_amount", dto.getTax_free_amount());
            params.put("approval_url", dto.getApproval_url());
            params.put("fail_url", dto.getFail_url());
            params.put("cancel_url", dto.getCancel_url());
            System.out.println(dto.getPartner_user_id());

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "SECRET_KEY " + secretKey);

            // 요청 본문과 헤더를 설정한 HttpEntity 객체 생성
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params, headers);

            // RestTemplate을 사용하여 POST 요청 보내기
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<Map> response = restTemplate.exchange(KAKAO_PAY_API_URL, HttpMethod.POST, entity, Map.class);

            // 카카오페이 응답 처리
            Map<String, Object> result = response.getBody();

            String redirectUrl = (String) result.get("next_redirect_pc_url");
            String tid = (String) result.get("tid"); // 이 값이 중요!


            // 결제 요청 성공시 리디렉션 URL을 반환
            PaymentResponseDto responseDto = new PaymentResponseDto();
            responseDto.setTid(tid);
            responseDto.setNext_redirect_pc_url(redirectUrl);


            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseDto.setFailed("결제 요청 실패: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<ApproveResponseDto> approvePayment(PaymentApproveRequestDto dto) {
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("cid", dto.getCid());              // 가맹점 코드(테스트용)
            parameters.put("tid", dto.getTid());                       // 결제 고유번호
            parameters.put("partner_order_id", dto.getPartner_order_id()); // 주문번호
            parameters.put("partner_user_id", dto.getPartner_user_id());    // 회원 아이디
            parameters.put("pg_token", dto.getPg_token());              // 결제승인 요청을 인증하는 토큰

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

            RestTemplate template = new RestTemplate();

            String url = "https://open-api.kakaopay.com/online/v1/payment/approve";

            ApproveResponseDto approveResponse = template.postForObject(url, requestEntity, ApproveResponseDto.class);

            // Payment 객체 생성
            Payment payment = Payment.builder()
                    .productId(Long.valueOf(dto.getProduct_id()))
                    .orderId(approveResponse.getPartner_order_id())
                    .paymentUserId(approveResponse.getPartner_user_id())
                    .created_time(approveResponse.getCreated_at())
                    .approved_time(approveResponse.getApproved_at())
                    .paymentType(approveResponse.getPayment_method_type())
                    .build();

            // 결제 정보 저장
            paymentRepository.save(payment);

            return ResponseDto.setSuccess("결제 승인 성공", approveResponse);
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseDto.setFailed("결제 승인 실패: " + e.getMessage());
        }
    }

    // 카카오페이 측에 요청 시 헤더부에 필요한 값
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "SECRET_KEY " + secretKey);
        headers.set("Content-type", "application/json");

        return headers;
    }
}