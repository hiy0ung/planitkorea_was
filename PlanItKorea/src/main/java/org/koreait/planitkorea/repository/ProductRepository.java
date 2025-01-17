package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
