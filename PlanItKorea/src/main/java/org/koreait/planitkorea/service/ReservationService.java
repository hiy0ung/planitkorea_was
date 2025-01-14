package org.koreait.planitkorea.service;

import org.koreait.planitkorea.dto.Reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.Reservation;

import java.util.List;

public interface ReservationService {
    ResponseDto<Reservation> createReservation(Long userId, CreateReservationRequestDto dto);

    ResponseDto<List<Reservation>> getMyReservation(Long userId);

    ResponseDto<Boolean> deleteReservation(Long userId, Long id);
}
