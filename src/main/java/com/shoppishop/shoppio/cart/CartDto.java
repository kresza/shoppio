package com.shoppishop.shoppio.cart;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonPropertyOrder({ "cartId", "cartItems", "totalAmount"})
class CartDto {
    
    private String cartId;
    
    private List<CartItemDto> cartItems;
    private Double totalAmount;
}
