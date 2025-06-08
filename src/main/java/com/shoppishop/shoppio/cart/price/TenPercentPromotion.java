package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import org.springframework.stereotype.Component;

@Component
class TenPercentPromotion {

    double addTenPercentTotalAmountPromotion(CartDto cart) {
        Double totalAmountWithoutPromo = cart.getTotalAmount();
        return totalAmountWithoutPromo * 0.9;
    }
}
