package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)

@Entity
@Table(name = "Products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private String productPrice;

    @Column(name = "productAddress", nullable = false)
    private String productAddress;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "product_category", nullable = false)
    private String productCategory;

=======
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private String productPrice;

    @Column(name = "productAddress")
    private String productAddress;

    @Column(name = "product_description")
    private String productDescription;

>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubProduct> subProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductCity> productCities;
<<<<<<< HEAD

    @ManyToMany
    @JoinTable(
            name = "Product_Facilities",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<Facility> facilities = new ArrayList<>();
=======
>>>>>>> bb0d688 (chore: 프로젝트 구조 정리 및 파일 이동)
}
