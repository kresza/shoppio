package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.products.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cart_item")
class CartItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "cart_id")
  private String cartId;

  @OneToOne
  @JoinColumn(name = "product_id")
  private ProductEntity productEntity;

  @Column(name = "quantity")
  private Integer quantity;
}
