package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.Reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.entity.Reservation;
import org.koreait.planitkorea.entity.Review;
import org.koreait.planitkorea.repository.ReservationRepository;
import org.koreait.planitkorea.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public ResponseDto<Reservation> createReservation(Long userId, CreateReservationRequestDto dto) {
        Reservation data = null;
        Long productId = dto.getProductId();
        Long subProductId = dto.getSubProductId();
        Long person = dto.getPerson();
        String totalPrice = dto.getTotalPrice();
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();

        try {
            data = Reservation.builder()
                    .id(null)
                    .userId(userId)
                    .productId(productId)
                    .subProductId(subProductId)
                    .person(person)
                    .totalPrice(totalPrice)
                    .startDate(startDate)
                    .endDate(endDate)
                    .reservationStatus(1)
                    .build();

            reservationRepository.save(data);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<List<Reservation>> getMyReservation(Long userId) {
        try {
            List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
            if (reservations.isEmpty()) {
                return ResponseDto.setSuccess( ResponseMessage.NOT_EXIST_DATA, null);
            }
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, reservations);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
    public ResponseDto<Boolean> deleteReservation(Long userId, Long id) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if(optionalReservation.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.NOT_EXIST_DATA, false);
            }

            Reservation deleteData = optionalReservation.get();

            if(!deleteData.getUserId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

            reservationRepository.deleteById(id);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        // 예약 상태 로직 ?
        // 마이페지이 예약내역리스트 get요청이 들어오면 프론트에서 하나하나 까서
        // 현재시간이 endDate 뵤댜 지난 상태면 update 요청?
    }



















}
