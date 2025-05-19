package com.shoppishop.shoppio.cart.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItem {
    
    @NotNull
    @Min(value = 1, message = "ProductId must be at least 1")
    private Long productId;
    
    @NotNull
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
