package com.shoppishop.shoppio.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cart")
class CartEntity {

  @Id
  @Column(name = "cart_id")
  private String cartId;

  @Column(name = "created_at")
  private Timestamp createdAt;


  @OneToMany
  @JoinColumn(name = "cart_id")
  private List<CartItemEntity> cartItemEntity;
}
