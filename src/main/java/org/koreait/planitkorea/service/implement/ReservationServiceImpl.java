package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;

import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.reservation.request.CreateReservationRequestDto;
import org.koreait.planitkorea.dto.reservation.response.GetMyReservationResponseDto;
<<<<<<< HEAD
import org.koreait.planitkorea.dto.reservation.response.GetOrderIdByReservationDto;
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
import org.koreait.planitkorea.dto.reservation.response.ReservationResponseDto;
import org.koreait.planitkorea.entity.Product;
import org.koreait.planitkorea.entity.Reservation;
import org.koreait.planitkorea.entity.SubProduct;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.repository.ProductRepository;
import org.koreait.planitkorea.repository.ReservationRepository;
import org.koreait.planitkorea.repository.SubProductRepository;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.ReservationService;
<<<<<<< HEAD
import org.springframework.dao.DataIntegrityViolationException;
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;

    @Override
    public ResponseDto<ReservationResponseDto> createReservation(Long userId, CreateReservationRequestDto dto) {
        ReservationResponseDto data = null;

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_USER));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_DATA));
        SubProduct subProduct = subProductRepository.findById(dto.getSubProductId())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXIST_DATA));

<<<<<<< HEAD
        if (dto.getPerson() == null || dto.getTotalPrice() == null || dto.getStartDate() == null || dto.getEndDate() == null) {
            throw new IllegalArgumentException(ResponseMessage.VALIDATION_FAIL);
        }

        try {
            Reservation reservation = Reservation.builder()
                    .user(user)
                    .product(product)
                    .subProduct(subProduct)
                    .person(dto.getPerson())
                    .totalPrice(dto.getTotalPrice())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .orderId(dto.getOrderId())
=======
        Long person = dto.getPerson();
        String totalPrice = dto.getTotalPrice();
        LocalDate startDate = dto.getStartDate();
        LocalDate endDate = dto.getEndDate();

        try {
            Reservation reservation = Reservation.builder()
                    .id(null)
                    .user(user)
                    .product(product)
                    .subProduct(subProduct)
                    .person(person)
                    .totalPrice(totalPrice)
                    .startDate(LocalDate.from(startDate))
                    .endDate(LocalDate.from(endDate))
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
                    .reservationStatus(1)
                    .build();

            reservationRepository.save(reservation);
            data = new ReservationResponseDto(reservation);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

<<<<<<< HEAD
        } catch (DataIntegrityViolationException e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        } catch (Exception e) {
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

=======
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)

    @Override
    public ResponseDto<List<GetMyReservationResponseDto>> getMyReservation(Long userId) {
        try {
            List<Object[]> results = reservationRepository.findAllByUserId(userId);

            if(results.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.SUCCESS, null);
            }

            List<GetMyReservationResponseDto> data = results.stream()
                    .map(result -> {
                        Reservation reservation = (Reservation) result[0];
                        String productImage = (String) result[1];
                        String productName = (String) result[2];
                        return new GetMyReservationResponseDto(
                                reservation.getId(),
                                reservation.getUser().getId(),
                                reservation.getProduct().getId(),
                                reservation.getSubProduct().getId(),
                                reservation.getPerson(),
                                reservation.getTotalPrice(),
                                reservation.getStartDate().atStartOfDay(),
                                reservation.getEndDate().atStartOfDay(),
<<<<<<< HEAD
                                reservation.getOrderId(),
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
                                reservation.getReservationStatus(),
                                productImage,
                                productName
                        );
                    }).collect(Collectors.toList());
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

    }

    @Override
<<<<<<< HEAD
    public ResponseDto<Boolean> deleteReservation(Long userId, Long reservationId) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
=======
    public ResponseDto<Boolean> deleteReservation(Long userId, Long id) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
            if(optionalReservation.isEmpty()) {
                return ResponseDto.setSuccess(ResponseMessage.NOT_EXIST_DATA, false);
            }

            Reservation deleteData = optionalReservation.get();

            if(!deleteData.getUser().getId().equals(userId)) {
                return ResponseDto.setFailed(ResponseMessage.NO_PERMISSION);
            }

<<<<<<< HEAD
            reservationRepository.deleteById(reservationId);
=======
            reservationRepository.deleteById(id);
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, true);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }

        // 예약 상태 로직 ?
        // 마이페지이 예약내역리스트 get요청이 들어오면 프론트에서 하나하나 까서
        // 현재시간이 endDate 뵤댜 지난 상태면 update 요청?
    }

<<<<<<< HEAD
    @Override
    public ResponseDto<GetOrderIdByReservationDto> getOrderIdReservation(String orderId) {
        try {
            Optional<Reservation> optionalReservation = reservationRepository.findByOrderId(orderId);

            if (optionalReservation.isEmpty()) {
                return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_DATA);
            }

            Reservation reservationData = optionalReservation.get();

            GetOrderIdByReservationDto data = new GetOrderIdByReservationDto(reservationData);

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }
=======
















>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)


}
