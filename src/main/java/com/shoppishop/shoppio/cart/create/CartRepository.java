package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.retrieve.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findTopByOrderByCartIdDesc();
    @Query( value = "DELETE FROM cart c WHERE c.cart_id = :cartId",nativeQuery = true)
    void deleteByCartId(String cartId);
}
