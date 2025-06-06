package org.koreait.planitkorea.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Facilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String facilityName;

    @ManyToMany(mappedBy = "facilities")
    private List<Product> products = new ArrayList<>();
}
