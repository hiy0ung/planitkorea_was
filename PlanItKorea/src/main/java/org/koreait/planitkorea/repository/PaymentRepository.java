package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
