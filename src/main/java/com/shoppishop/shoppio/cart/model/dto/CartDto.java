package com.shoppishop.shoppio.cart.model.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "cartId", "cartItems", "totalAmount"})
public class CartDto {
    
    private String cartId;
    
    private List<CartItemDto> cartItems;
    private Double totalAmount;
}
