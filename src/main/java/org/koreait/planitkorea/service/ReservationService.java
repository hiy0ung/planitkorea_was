package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.reservation.response.GetMyReservationResponseDto;
<<<<<<< HEAD
import org.koreait.planitkorea.dto.reservation.response.GetOrderIdByReservationDto;
import org.koreait.planitkorea.dto.reservation.response.ReservationResponseDto;
import org.koreait.planitkorea.entity.Reservation;
=======
import org.koreait.planitkorea.dto.reservation.response.ReservationResponseDto;
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)

import java.util.List;

public interface ReservationService {
    ResponseDto<ReservationResponseDto> createReservation(Long userId, CreateReservationRequestDto dto);

    ResponseDto<List<GetMyReservationResponseDto>> getMyReservation(Long userId);

<<<<<<< HEAD
    ResponseDto<Boolean> deleteReservation(Long userId, Long reservationId);

    ResponseDto<GetOrderIdByReservationDto> getOrderIdReservation(String orderId);
=======
    ResponseDto<Boolean> deleteReservation(Long userId, Long id);
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
}
