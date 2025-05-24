package com.shoppishop.shoppio.cart.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.shoppishop.shoppio.catalogue.products.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({ "product", "quantity" })
public class CartItemDto {
    
    private ProductDto product;
    private Integer quantity;

}
