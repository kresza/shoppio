package com.shoppishop.shoppio.cart.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateCartItem {
    @NotNull
    @Min(1)
    @Pattern(regexp = "\\d+", message = "ProductId must contain only digits")
    private Long productId;
    @NotNull
    @Min(1)
    @Pattern(regexp = "\\d+", message = "Quantity must contain only digits")
    private Integer quantity;
}
