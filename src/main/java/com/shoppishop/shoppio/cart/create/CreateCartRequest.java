package com.shoppishop.shoppio.cart.create;


import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class CreateCartRequest {

    @Valid
    private List<CreateCartItem> items;
}
