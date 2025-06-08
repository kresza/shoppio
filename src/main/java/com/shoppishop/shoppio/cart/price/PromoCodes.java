package com.shoppishop.shoppio.cart.price;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromoCodes {
    ALL_10_PROMOTION("ALL10", "10% cheaper for all products"),
    EVERY3_PROMOTION("EVERY3", "buy 3 products, the cheapest for 1 pln"),
    SECOND_HALF_PRICE_PROMOTION("SECOND50", "second the same product 50% cheaper");

    private final String promoCode;
    private final String description;
}
