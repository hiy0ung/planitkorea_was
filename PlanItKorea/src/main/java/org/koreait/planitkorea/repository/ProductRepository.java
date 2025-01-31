package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = """

            select
                p.id as product_id,
                p.product_name,
                p.product_price,
                p.product_address,
                pi.product_image,
                sp.id as sub_product_id,
                sp.sub_name,
                sp.sub_price,
                sp.sub_person,
                spi.sub_product_image,
                r.id as reservation_id,
                r.start_date,
                r.end_date
            from Products p
            inner join Product_images pi on pi.product_id = p.id
            inner join Product_Cities pc on pc.product_id = p.id
            inner join cities c on c.id = pc.city_id
            inner join Sub_Products sp on sp.main_product_id = p.id
            inner join sub_product_images spi on spi.sub_product_id = sp.id
            left outer join Reservations r on r.sub_product_id = sp.id
            where c.id = :cityName
            AND sp.sub_person >= :person
            AND (
                r.id IS NULL OR
            NOT (DATE(r.start_date) <= :startDate AND DATE(r.end_date) >= :endDate)
            );
""", nativeQuery = true)
    List<Object[]> findAllProductsByCityAndDate(@Param("cityName") String cityName,
                                                @Param("person") int person,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}
