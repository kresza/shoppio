package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.TestStubs;
import com.shoppishop.shoppio.cart.model.dto.CartDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TotalAmountEnrichmentTest implements TestStubs {

    @InjectMocks TotalAmountEnrichment totalAmountEnrichment;

    @Test
    void shouldCalculateAndEnrichCartWithTotalAmount() {
        // given
        CartDto givenCartDto = createCartDto();
        double expectedAmount = 200.00;

        // when
        CartDto cartDto = totalAmountEnrichment.calculateAndEnrichCartWithTotalAmount(givenCartDto);

        // then
        assertThat(cartDto)
                .usingRecursiveComparison()
                .ignoringFields("totalAmount")
                .isEqualTo(givenCartDto);

        assertThat(cartDto.getTotalAmount()).isEqualTo(expectedAmount);
    }
}
