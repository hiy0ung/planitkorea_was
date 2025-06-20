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
    // 전체 조회
    @Query(value = """
      WITH Available_Rooms AS (
           SELECT
               p.id AS product_id,
               p.product_category,
               p.product_name,
               p.product_price,
               p.product_address,
               p.product_description,
               (SELECT pi.product_image
                FROM Product_images pi
                WHERE pi.product_id = p.id
                ORDER BY pi.id ASC
                LIMIT 1) AS product_image
           FROM Products p
           INNER JOIN Product_Cities pc ON pc.product_id = p.id
           INNER JOIN Cities c ON c.id = pc.city_id
           INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
           WHERE c.city_name = :cityName
           AND sp.sub_person >= :person
           AND NOT EXISTS (
               SELECT 1
               FROM Reservations r
               WHERE r.sub_product_id = sp.id
               AND r.start_date <= :endDate
               AND r.end_date >= :startDate
           )
       )
       SELECT
           ar.product_id,
           ar.product_category,
           ar.product_name,
           ar.product_price,
           ar.product_address,
           ar.product_description,
           ar.product_image,
           GROUP_CONCAT(f.id) AS facilityIds
        FROM Available_Rooms ar
        LEFT JOIN Product_Facilities pf ON pf.product_id = ar.product_id
        LEFT JOIN Facilities f ON f.id = pf.facility_id
        GROUP BY 
            ar.product_id, 
            ar.product_category, 
            ar.product_name, 
            ar.product_price, 
            ar.product_address, 
            ar.product_description, 
            ar.product_image;
""", nativeQuery = true)
    List<Object[]> findAllProductsByCityAndDate(@Param("cityName") String cityName,
                                                @Param("person") int person,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
    // 상세 조회
    @Query(value = """
        WITH Available_Rooms AS (
            SELECT
                p.id AS product_id,
                p.product_category,
                p.product_name,
                p.product_price,
                p.product_address,
                p.product_description,
                pi.product_image,
                sp.id AS sub_product_id,
                sp.sub_name,
                sp.sub_price,
                sp.sub_person,
                spi.sub_product_image
            FROM Products p
            LEFT JOIN Product_images pi ON pi.product_id = p.id
            INNER JOIN Sub_Products sp ON sp.main_product_id = p.id
            INNER JOIN sub_product_images spi ON spi.sub_product_id = sp.id
            WHERE p.id = :productId
        )
        SELECT
            ar.product_id,
            ar.product_category,
            ar.product_name,
            ar.product_price,
            ar.product_address,
            ar.product_description,
            ar.product_image,
            ar.sub_product_id,
            ar.sub_name,
            ar.sub_price,
            ar.sub_person,
            ar.sub_product_image,
            f.id as facility_id,
            f.facility_name
        FROM Available_Rooms ar
        LEFT JOIN Product_Facilities pf ON pf.product_id = ar.product_id
        LEFT JOIN Facilities f ON f.id = pf.facility_id;
""", nativeQuery = true)
    List<Object[]> findProductDetailById(@Param("productId") Long productId);

    @Query(value = """
    SELECT
        p.product_id,
        p.product_name,
        p.product_category AS accommodation_type,
        p.city_name AS accommodation_region,
        p.product_address AS accommodation_address, 
        p.product_price AS accommodation_price, 
        pi.image AS accommodation_image,
        wishlist_count
    FROM (
        SELECT
            p.id AS product_id,
            p.product_name,
            p.product_category,
            c.city_name AS city_name,
            p.product_address,
            p.product_price,
            COUNT(wl.product_id) AS wishlist_count
        FROM Products p
        LEFT JOIN Wish_List wl ON p.id = wl.product_id
        JOIN Product_Cities pc ON pc.product_id = p.id
        JOIN Cities c ON pc.city_id = c.id
        GROUP BY p.id, p.product_name, p.product_category, c.city_name, p.product_address, p.product_price
        HAVING COUNT(wl.product_id) > 0
        ORDER BY wishlist_count DESC
        LIMIT 5
    ) AS p
    LEFT JOIN (
        SELECT pi.product_id, pi.product_image AS image
        FROM Product_Images pi
        INNER JOIN (
            SELECT product_id, MIN(id) AS min_id
            FROM Product_Images
            GROUP BY product_id
        ) AS first_images ON pi.id = first_images.min_id
    ) AS pi ON p.product_id = pi.product_id
""", nativeQuery = true)
    List<Object[]> findTop5Products();

//    @Query(value = """
//    SELECT
//        p.id AS product_id,
//        p.product_name AS product_name,
//        p.product_category AS accommodation_type,
//        c.city_name AS accommodation_region,
//        p.product_address AS accommodation_address,
//        p.product_price AS accommodation_price,
//        pi.product_image AS accommodation_image,
//        COUNT(wl.product_id) AS wishlist_count
//    FROM Products p
//    LEFT JOIN Wish_List wl ON p.id = wl.product_id
//    JOIN Product_Cities pc ON pc.product_id = p.id
//    JOIN cities c ON pc.city_id = c.id
//    LEFT JOIN Product_Images pi ON pi.product_id = p.id
//    GROUP BY p.id, p.product_name, p.product_category, c.city_name, p.product_address, p.product_price, pi.product_image
//    HAVING COUNT(wl.product_id) > 0
//    ORDER BY wishlist_count DESC
//    LIMIT 5;
//
//""", nativeQuery = true)
//    List<Object[]> findTop5Products();
}
