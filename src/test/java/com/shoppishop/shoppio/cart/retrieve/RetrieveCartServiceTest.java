package com.shoppishop.shoppio.cart.retrieve;

import com.shoppishop.shoppio.cart.TestStubs;
import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.cart.price.TotalAmountEnrichment;
import com.shoppishop.shoppio.models.BaseResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveCartServiceTest implements TestStubs {

    @Mock RetrieveCartRepository retrieveCartRepository;

    @Mock TotalAmountEnrichment totalAmountEnrichment;

    @InjectMocks RetrieveCartService retrieveCartService;

    @Test
    void shouldGetCartById() {
        // given
        when(retrieveCartRepository.findCartById(CART_ID)).thenReturn(createCart());

        when(totalAmountEnrichment.calculateAndEnrichCartWithTotalAmount(any()))
                .thenReturn(CartDto.builder().cartId(CART_ID).totalAmount(200.00).build());
        // when
        BaseResponse baseResponse = retrieveCartService.getCartById(CART_ID, null);

        // then
        assertThat(baseResponse).isNotNull();
        assertThat(baseResponse.getData()).isNotNull();
        assertThat(baseResponse.getData()).isInstanceOf(List.class);
        assertThat(baseResponse.getData().get(0))
                .satisfies(
                        cart -> {
                            if (cart instanceof CartDto cartDto) {
                                assertThat(cartDto.getCartId()).isEqualTo(CART_ID);
                                assertThat(cartDto.getTotalAmount()).isEqualTo(200.00);
                            }
                        });
    }
}
