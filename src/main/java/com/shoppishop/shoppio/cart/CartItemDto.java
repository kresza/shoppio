package com.shoppishop.shoppio.cart;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shoppishop.shoppio.products.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "product", "quantity" })
class CartItemDto {
    
    private ProductDto product;
    private Integer quantity;

}
