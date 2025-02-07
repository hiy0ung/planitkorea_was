package org.koreait.planitkorea.controller;

import lombok.RequiredArgsConstructor;
import org.koreait.planitkorea.dto.product.response.ProductDetailResponseDto;
import org.koreait.planitkorea.dto.product.response.ProductListResponseDto;
import org.koreait.planitkorea.dto.ResponseDto;
import org.koreait.planitkorea.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<ProductListResponseDto>>> searchAllProduct(
            @RequestParam String cityName,
            @RequestParam int person,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        ResponseDto<List<ProductListResponseDto>> response = productService.searchAllProduct(cityName, person, startDate, endDate);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductDetailResponseDto>> getProductDetail(@PathVariable Long id) {
        ResponseDto<ProductDetailResponseDto> response = productService.getProductDetail(id);
        HttpStatus status = response.isResult() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(response);
    }
}
