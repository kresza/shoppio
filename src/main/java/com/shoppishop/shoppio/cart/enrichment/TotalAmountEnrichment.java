package com.shoppishop.shoppio.cart.enrichment;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TotalAmountEnrichment {

    public CartDto calculateAndEnrichCartWithTotalAmount(CartDto cartDto) {
        double totalAmount = calculateTotalAmount(cartDto);
        cartDto.setTotalAmount(roundAmountHalfUp(totalAmount));
        return cartDto;
    }

    private double roundAmountHalfUp(double totalAmount) {
        return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double calculateTotalAmount(CartDto cartDto) {
        return cartDto.getCartItems().stream()
                .mapToDouble(
                        cartItemDto ->
                                cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity())
                .sum();
    }
}
