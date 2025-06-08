package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TenPercentPromotionTest {

    @InjectMocks TenPercentPromotion tenPercentPromotion;

    @Test
    void shouldAddTenPercentTotalAmountPromotion() {
        // given
        double givenAmount = 100.00;
        double expectedAmount = 90.00;
        CartDto cartDto = CartDto.builder().totalAmount(givenAmount).build();

        // when
        double promotion = tenPercentPromotion.addTenPercentTotalAmountPromotion(cartDto);

        // then
        assertThat(promotion).isEqualTo(expectedAmount);
    }
}
