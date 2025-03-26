package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "payment_user_id", nullable = false)
    private String paymentUserId;

    @Column(name = "created_time", nullable = false)
    private String created_time;

    @Column(name = "approved_time", nullable = false)
    private String approved_time;

    @Column(name = "payment_type", nullable = false)
    private String paymentType;
}
