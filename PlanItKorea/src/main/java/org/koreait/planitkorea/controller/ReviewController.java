package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.common.constant.ApiMappingPattern;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.dto.review.request.CreateRequestDto;
import org.koreait.planitkorea.dto.review.response.CreateResponseDto;
import org.koreait.planitkorea.dto.review.response.ProductReviewResponseDto;
import org.koreait.planitkorea.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiMappingPattern.REVIEW)
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    public static final String GET_PRODUCT_REVIEWS = "/{productId}";

    // 리뷰 등록
    @PostMapping
    public ResponseEntity<ResponseDto<CreateResponseDto>> createReview (
            @AuthenticationPrincipal Long userId,
            @RequestBody CreateRequestDto dto
            ) {
        ResponseDto<CreateResponseDto> response = reviewService.createReview(userId, dto);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    // 상품 내 리뷰 조회
    @GetMapping(GET_PRODUCT_REVIEWS)
    public ResponseEntity<ResponseDto<List<ProductReviewResponseDto>>> getProductReviews (
            @PathVariable Long productId
    ) {
        ResponseDto<List<ProductReviewResponseDto>> response = reviewService.getProductReviews(productId);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
