package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.cart.create.CreateCartItem;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractCartRequest {
    @Valid
    private List<CreateCartItem> items;
}
