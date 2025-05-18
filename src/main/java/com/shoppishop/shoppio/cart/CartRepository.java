package com.shoppishop.shoppio.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "SELECT * FROM cart c WHERE c.cart_id = :cartId", nativeQuery = true)
    List<CartEntity> findCartById(String cartId);
}
