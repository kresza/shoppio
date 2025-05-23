package com.shoppishop.shoppio.cart.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItem {

    @NotNull
    @Schema(description = "ProductId", example = "1")
    @Min(value = 1, message = "ProductId must be at least 1")
    private Long productId;

    @NotNull
    @Schema(description = "Number of products", example = "10")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
