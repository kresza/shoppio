package com.shoppishop.shoppio.cart.model;

import com.shoppishop.shoppio.cart.model.dto.CreateCartItem;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractCartRequest {
    @Valid
    private List<CreateCartItem> items;
}
