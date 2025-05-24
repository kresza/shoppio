package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import com.shoppishop.shoppio.cart.model.entity.CartItemEntity;
import com.shoppishop.shoppio.catalogue.products.ProductEntity;
import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ResponseMessage;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.InternalException;

import java.util.List;
import java.util.Optional;

@UtilityClass
public class CartUtils {
    private static final String CART_SUCCESSFULLY_CREATED = "Cart successfully created";
    public static final String PRODUCT_NOT_FOUND_ERROR = "Product with given id: %d not found";

    public BaseResponse buildCartCreateResponse(String generatedCartId) {
        return BaseResponse.builder()
                .data(List.of(generatedCartId))
                .warnings(
                        List.of(
                                ResponseMessage.builder()
                                        .message(CART_SUCCESSFULLY_CREATED)
                                        .code(201L)
                                        .build()))
                .build();
    }

    public Optional<CartItemEntity> findExistingItem(
            Long productId, CartEntity cartEntity) {
        return cartEntity.getCartItemEntity().stream()
                .filter(cartItem -> cartItem.getProductEntity().getId().equals(productId))
                .findAny();
    }

    public ResponseMessage buildSuccessfullyAddedProductMessage(String cartId) {
        return ResponseMessage.builder()
                .message(String.format("Product successfully added to cart %s", cartId))
                .build();
    }

    public ResponseMessage buildSuccessfullyProductRemovedMessage(String cartId) {
        return ResponseMessage.builder()
                .message(String.format("Product successfully removed from cart %s", cartId))
                .build();
    }

    public ResponseMessage buildCartNotFoundMessage() {
        return ResponseMessage.builder().message("Cart not found").code(503L).build();
    }


    public CartEntity mapToCartEntity(List<CartItemEntity> items, String generatedCartId) {
        return CartEntity.builder().cartItemEntity(items).cartId(generatedCartId).build();
    }

    public static CartItemEntity buildCartItem(
            Integer quantity, CartEntity cartEntity, ProductEntity productEntity) {
        return CartItemEntity.builder()
                .cartId(cartEntity.getCartId())
                .quantity(quantity)
                .productEntity(productEntity)
                .build();
    }

    public static BaseResponse buildCartNotFoundResponse() {
        return BaseResponse.builder()
                .errors(List.of(CartUtils.buildCartNotFoundMessage()))
                .build();
    }

    public CartItemEntity addQuantity(Integer quantity, CartItemEntity cartItemEntity) {
        int existQuantity = cartItemEntity.getQuantity();
        cartItemEntity.setQuantity(existQuantity + quantity);
        return cartItemEntity;
    }

    public CartItemEntity removeQuantity(Integer quantity, CartItemEntity cartItemEntity) {
        int existQuantity = cartItemEntity.getQuantity();
        cartItemEntity.setQuantity(existQuantity - quantity);
        return cartItemEntity;
    }
}
