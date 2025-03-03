package org.koreait.planitkorea.service.implement;

import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.pay.request.PaymentApproveRequestDto;
import org.koreait.planitkorea.dto.pay.request.PaymentRequestDto;
import org.koreait.planitkorea.dto.pay.response.PaymentResponseDto;
import org.koreait.planitkorea.service.KakaoPayService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

    @Value("${kakao.pay.client-id}")
    private String clientId;

    @Value("${kakao.pay.secret-key}")
    private String secretKey;

    private final String KAKAO_PAY_API_URL = "https://open-api.kakaopay.com/online/v1/payment/ready"; // 결제 요청 URL
    private final String KAKAO_PAY_APPROVE_URL = "https://kapi.kakao.com/v1/payment/approve"; // 결제 승인 URL





    @Override
    public ResponseDto<PaymentResponseDto> requestPayment(PaymentRequestDto dto) {
        try {
            // 요청 파라미터 설정
            Map<String, Object> params = new HashMap<>();
            params.put("cid", clientId);
            params.put("partner_order_id", dto.getPartner_order_id());
            params.put("partner_user_id", dto.getPartner_user_id());
            params.put("item_name", dto.getItem_name());
            params.put("quantity", dto.getQuantity());
            params.put("total_amount", dto.getTotal_amount());
            params.put("tax_free_amount", dto.getTax_free_amount());
            params.put("approval_url", dto.getApproval_url());
            params.put("fail_url", dto.getFail_url());
            params.put("cancel_url", dto.getCancel_url());

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "SECRET_KEY " + secretKey);

            // 요청 본문과 헤더를 설정한 HttpEntity 객체 생성
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params, headers);

            // RestTemplate을 사용하여 POST 요청 보내기
            RestTemplate restTemplate = new RestTemplate();

            // URL 로그 확인
            System.out.println("Kakao Pay API URL: " + KAKAO_PAY_API_URL);
            System.out.println("Request Headers: " + headers);

            ResponseEntity<Map> response = restTemplate.exchange(KAKAO_PAY_API_URL, HttpMethod.POST, entity, Map.class);

            // 카카오페이 응답 처리
            Map<String, Object> result = response.getBody();
            String redirectUrl = (String) result.get("next_redirect_pc_url");

            // 결제 요청 성공시 리디렉션 URL을 반환
            PaymentResponseDto responseDto = new PaymentResponseDto();
            responseDto.setTid((String) result.get("tid"));
            responseDto.setNext_redirect_pc_url(redirectUrl);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, responseDto);
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseDto.setFailed("결제 요청 실패: " + e.getMessage());
        }
    }

    @Override
    public ResponseDto<String> approvePayment(PaymentApproveRequestDto dto) {
        try {
            // 결제 승인 파라미터 준비
            Map<String, String> params = new HashMap<>();
            params.put("cid", clientId); // 카카오페이 클라이언트 ID
            params.put("tid", dto.getTid()); // 거래 ID
            params.put("pg_token", dto.getPg_token()); // PG 토큰

            // 요청 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "KakaoAK " + secretKey); // 인증을 위한 헤더
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // 요청 본문 설정
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(params, headers);

            // RestTemplate으로 카카오페이 결제 승인 요청
            RestTemplate restTemplate = new RestTemplate();

            // URL 로그 확인
            System.out.println("Kakao Pay Approval URL: " + KAKAO_PAY_APPROVE_URL);
            System.out.println("Request Headers: " + headers);

            restTemplate.exchange(KAKAO_PAY_APPROVE_URL, HttpMethod.POST, entity, String.class);

            // 결제 승인 성공 메시지 반환
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, "승인 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("결제 승인 실패: " + e.getMessage());
        }
    }


}
