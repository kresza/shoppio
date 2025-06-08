package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.TestStubs;
import com.shoppishop.shoppio.cart.retrieve.RetrieveCartRepository;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest implements TestStubs {

    @Mock RetrieveCartRepository retrieveCartRepository;
    @Mock CartItemRepository cartItemRepository;

    @InjectMocks CartService cartService;

    @Test
    void shouldDeleteProduct() {
        // given
        long productId = 1L;
        long cartId = 1L;
        when(retrieveCartRepository.findCartById(CART_ID)).thenReturn(createCart());
        doNothing().when(cartItemRepository).deleteItemByCartId(productId);
        // when
        BaseResponse response = cartService.deleteProduct(CART_ID, productId, PRODUCT_QUANTITY);
        // then
        verify(cartItemRepository).deleteItemByCartId(cartId);
        assertThat(response).isNotNull();
        assertThat(response.getWarnings().get(0).getMessage())
                .isEqualTo(buildResponseMessage().getMessage());
    }

    private ResponseMessage buildResponseMessage() {
        return ResponseMessage.builder()
                .message(String.format("Product successfully removed from cart %s", CART_ID))
                .build();
    }
}
