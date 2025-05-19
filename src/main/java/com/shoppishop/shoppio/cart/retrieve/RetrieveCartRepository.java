package com.shoppishop.shoppio.cart.retrieve;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface RetrieveCartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "SELECT * FROM cart c WHERE c.cart_id = :cartId", nativeQuery = true)
    List<CartEntity> findCartById(String cartId);
}
