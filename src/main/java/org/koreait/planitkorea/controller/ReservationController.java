package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.reservation.response.GetMyReservationResponseDto;
import org.koreait.planitkorea.dto.reservation.response.GetOrderIdByReservationDto;
import org.koreait.planitkorea.dto.reservation.response.ReservationResponseDto;
import org.koreait.planitkorea.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiMappingPattern.RESERVATION)
public class ReservationController {

    private final ReservationService reservationService;

    private static final String POST_RESERVATION = "";
    private static final String GET_ORDER_ID = "/{orderId}";


    // 예약 등록
    @PostMapping(POST_RESERVATION)
    public ResponseEntity<ResponseDto<ReservationResponseDto>> createReservation (
            @AuthenticationPrincipal Long userId,
            @RequestBody CreateReservationRequestDto dto
    ) {
        ResponseDto<ReservationResponseDto> response = reservationService.createReservation(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 내 예약 확인
    @GetMapping
    public ResponseEntity<ResponseDto<List<GetMyReservationResponseDto>>> getMyReservation (
            @AuthenticationPrincipal Long userId
    ) {
        ResponseDto<List<GetMyReservationResponseDto>> response = reservationService.getMyReservation(userId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 예약 삭제
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<ResponseDto<Boolean>> deleteReservation (
            @AuthenticationPrincipal Long userId,
            @PathVariable Long reservationId
    ) {
        ResponseDto<Boolean> response = reservationService.deleteReservation(userId, reservationId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 예약번호로 조회
    @GetMapping(GET_ORDER_ID)
    public ResponseEntity<ResponseDto<GetOrderIdByReservationDto>> getOrderIdReservation(@PathVariable String orderId) {
        ResponseDto<GetOrderIdByReservationDto> response = reservationService.getOrderIdReservation(orderId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }








}
