package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findTopByOrderByCartIdDesc();

    @Transactional
    @Modifying
    @Query( value = "DELETE FROM cart c WHERE c.cart_id = :cartId",nativeQuery = true)
    void deleteByCartId(String cartId);

    Optional<CartEntity> findByCartId(@Param("cartId") String cartId);
}
