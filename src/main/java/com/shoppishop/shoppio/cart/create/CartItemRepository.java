package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_item WHERE id = :id", nativeQuery = true)
    void deleteItemByCartId(@Param("id") Long id);
}
