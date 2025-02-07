package org.koreait.planitkorea.dto.product.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListResponseDto {
    private Long productId;
    private String productCategory;
    private String productName;
    private String productPrice;
    private String productDescription;
    private String productAddress;
    private String productImage;
    private Long facilityId;
}
