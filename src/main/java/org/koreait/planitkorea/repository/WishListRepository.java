package org.koreait.planitkorea.repository;

import org.koreait.planitkorea.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    Optional<WishList> findByUserIdAndId(Long userId, Long id);

    @Query(value = """
        SELECT
            w.id as wish_list_id,
            w.user_id,
            w.product_id,
            p.product_name,
            p.product_address,
            p.product_price,
            (
                SELECT pi.product_image
                FROM product_images pi
                WHERE pi.product_id = p.id
                ORDER BY pi.id asc
                LIMIT 1
            ) as product_image
        FROM wish_list w
        LEFT JOIN products p on p.id = w.product_id
        WHERE w.user_id = :userId
""", nativeQuery = true)
    List<Object[]> getWishListByUserId(@Param("userId") Long userId);
}
