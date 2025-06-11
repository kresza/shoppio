package com.shoppishop.shoppio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    FETCHING_PRODUCTS_ERROR(500L, "Failed to fetch products"),
    FETCHING_CARTS_ERROR(501L, "Failed to fetch carts"),
    FETCHING_CART_ERROR(502L, "Failed to fetch cart id: "),
    NOT_FOUND_CART_ERROR(504L, "Cart id not found: "),
    FETCHING_CATEGORIES_ERROR(503L, "Failed to fetch categories");

    private final Long code;
    private final String message;
}
