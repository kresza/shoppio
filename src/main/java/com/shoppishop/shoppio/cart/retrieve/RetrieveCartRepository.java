package com.shoppishop.shoppio.cart.retrieve;

import java.util.List;

import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RetrieveCartRepository extends JpaRepository<CartEntity, Long> {

    @Query(value = "SELECT * FROM cart c WHERE c.cart_id = :cartId", nativeQuery = true)
    CartEntity findCartById(String cartId);
}
