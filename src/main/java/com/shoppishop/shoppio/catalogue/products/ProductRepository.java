package com.shoppishop.shoppio.catalogue.products;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  List<ProductEntity> findAllByOrderByNameAsc();

  @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.categoryEntity.name) = LOWER(:name) ORDER BY p.price ASC")
  List<ProductEntity> findByCategoryNameOrderByPriceAsc(@Param("name") String name);

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM cart_item ci WHERE ci.cart_id = :cartId", nativeQuery = true)
  void deleteAllByCartId(String cartId);
}
