package org.koreait.planitkorea.service.implement;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ResponseMessage;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.review.request.CreateRequestDto;
import org.koreait.planitkorea.dto.review.response.CreateResponseDto;
import org.koreait.planitkorea.dto.review.response.ProductReviewResponseDto;
import org.koreait.planitkorea.entity.Review;
import org.koreait.planitkorea.entity.User;
import org.koreait.planitkorea.repository.ReviewRepository;
import org.koreait.planitkorea.repository.UserRepository;
import org.koreait.planitkorea.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseDto<CreateResponseDto> createReview(Long userId, CreateRequestDto dto) {
        Long productId = dto.getProductId();
        String reviewCommend = dto.getReviewCommend();
        CreateResponseDto data = null;

        try {
            Review review = Review.builder()
                    .id(null)
                    .productId(productId)
                    .userId(userId)
                    .reviewCommend(reviewCommend)
                    .reviewDate(new Date())
                    .build();
            reviewRepository.save(review);

            data = new CreateResponseDto(review);
            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }

    @Override
    public ResponseDto<List<ProductReviewResponseDto>> getProductReviews(Long productId) {
        try {
            List<Object[]> results = reviewRepository.findReviewsWithUserIdByProductId(productId);

            List<ProductReviewResponseDto> data = results.stream()
                    .map(result -> {
                        Review review = (Review) result[0];
                        String userStringId = (String) result[1];
                        return new ProductReviewResponseDto(
                                review.getId(),
                                review.getProductId(),
                                review.getUserId(),
                                review.getReviewCommend(),
                                review.getReviewDate(),
                                userStringId
                        );
                    }).collect(Collectors.toList());

            return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
    }


}
