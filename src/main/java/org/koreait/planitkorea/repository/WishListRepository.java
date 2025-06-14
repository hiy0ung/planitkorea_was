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
        select
            w.id as wish_list_id,
            w.user_id,
            w.product_id,
            p.product_name,
            p.product_address,
            p.product_price,
            (
                select pi.product_image
                from product_images pi
                where pi.product_id = p.id
                order by pi.id asc
                limit 1
            ) as product_image
        from wish_list w
        left join products p on p.id = w.product_id
        where w.user_id = :userId
""", nativeQuery = true)
    List<Object[]> getWishListByUserId(@Param("userId") Long userId);
}
