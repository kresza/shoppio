package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.cart.model.dto.CartItemDto;
import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import com.shoppishop.shoppio.cart.model.entity.CartItemEntity;
import com.shoppishop.shoppio.catalogue.products.ProductDto;
import com.shoppishop.shoppio.catalogue.products.ProductEntity;
import com.shoppishop.shoppio.dictionaries.category.CategoryEntity;

import java.util.List;

public interface TestStubs {
    String CART_ID = "C_000001";
    String CART_ID_2 = "C_000002";
    String PRODUCT_NAME = "Dell Laptop";
    double PRODUCT_PRICE = 100.00;
    int PRODUCT_QUANTITY = 2;

    default CartDto createCartDto() {
        return CartDto.builder().cartId(CART_ID).cartItems(List.of(buildCartItemDto())).build();
    }

    default CartEntity createCart() {
        return CartEntity.builder()
                .cartId(CART_ID)
                .cartItemEntity(
                        List.of(
                                CartItemEntity.builder()
                                        .productEntity(createProductEntity())
                                        .id(1L)
                                        .cartId(CART_ID)
                                        .quantity(PRODUCT_QUANTITY)
                                        .build()))
                .build();
    }

    private ProductEntity createProductEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName(PRODUCT_NAME);
        productEntity.setCategoryEntity(new CategoryEntity());
        productEntity.setPrice(PRODUCT_PRICE);
        productEntity.setAvailable(true);
        return productEntity;
    }

    private CartItemDto buildCartItemDto() {
        CartItemDto cartItemDto = new CartItemDto();
        ProductDto productDto = new ProductDto();
        productDto.setPrice(PRODUCT_PRICE);
        productDto.setName(PRODUCT_NAME);
        cartItemDto.setQuantity(PRODUCT_QUANTITY);
        cartItemDto.setProduct(productDto);
        return cartItemDto;
    }
}
