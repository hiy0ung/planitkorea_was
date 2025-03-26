package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.reservation.response.GetMyReservationResponseDto;
import org.koreait.planitkorea.dto.reservation.response.GetOrderIdByReservationDto;
import org.koreait.planitkorea.dto.reservation.response.ReservationResponseDto;
import org.koreait.planitkorea.entity.Reservation;

import java.util.List;

public interface ReservationService {
    ResponseDto<ReservationResponseDto> createReservation(Long userId, CreateReservationRequestDto dto);

    ResponseDto<List<GetMyReservationResponseDto>> getMyReservation(Long userId);

    ResponseDto<Boolean> deleteReservation(Long userId, Long id);

    ResponseDto<GetOrderIdByReservationDto> getOrderIdReservation(String orderId);
}
