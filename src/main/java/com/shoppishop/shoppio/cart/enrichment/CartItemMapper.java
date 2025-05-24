package com.shoppishop.shoppio.cart.enrichment;

import com.shoppishop.shoppio.cart.model.CreateCartRequest;
import com.shoppishop.shoppio.cart.model.dto.CreateCartItem;
import com.shoppishop.shoppio.cart.model.entity.CartItemEntity;
import com.shoppishop.shoppio.catalogue.products.ProductEntity;
import com.shoppishop.shoppio.catalogue.products.ProductFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartItemMapper {

    private final ProductFetcher productFetcher;

    public List<CartItemEntity> extractAndMapItemsToEntities(
            CreateCartRequest request, String generatedCartId) {
        return request.getItems().stream()
                .map(item -> mapCartItemToEntity(item, generatedCartId))
                .toList();
    }

    private CartItemEntity mapCartItemToEntity(CreateCartItem item, String generatedCartId) {
        ProductEntity productEntity = productFetcher.fetchProduct(item.getProductId());

        return CartItemEntity.builder()
                .cartId(generatedCartId)
                .quantity(item.getQuantity())
                .productEntity(productEntity)
                .build();
    }

}
