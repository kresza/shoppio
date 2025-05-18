package com.shoppishop.shoppio.products;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  List<ProductEntity> findAllByOrderByNameAsc();

  @Query("SELECT p FROM ProductEntity p WHERE LOWER(p.categoryEntity.name) = LOWER(:name) ORDER BY p.price ASC")
  List<ProductEntity> findByCategoryNameOrderByPriceAsc(@Param("name") String name);

}
