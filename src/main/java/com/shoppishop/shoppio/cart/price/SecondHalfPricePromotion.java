package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.cart.model.dto.CartItemDto;
import org.springframework.stereotype.Component;

@Component
public class SecondHalfPricePromotion {
    double addSecondHalfPricePromo(CartDto cartDto) {
        return cartDto.getCartItems().stream()
                .map(CartItemHalfPriceData::initialize)
                .mapToDouble(CartItemHalfPriceData::totalPriceWithHalfDiscount)
                .sum();
    }

    private record CartItemHalfPriceData(double price, int itemPairs, double soloProducts) {
        public static CartItemHalfPriceData initialize(CartItemDto cartItem) {
            return new CartItemHalfPriceData(
                    cartItem.getProduct().getPrice(),
                    cartItem.getQuantity() / 2,
                    cartItem.getQuantity() % 2);
        }

        public double totalPriceWithHalfDiscount() {
            return itemPairs * (price + price * 0.5) + soloProducts * price;
        }
    }
}
