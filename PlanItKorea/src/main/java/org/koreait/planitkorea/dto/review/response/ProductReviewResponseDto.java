package org.koreait.planitkorea.dto.review.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewResponseDto {
    private Long id;

    private Long productId;

    private Long userId;

    private String reviewCommend;

    private Date reviewDate;

    private String userStringId;
}
