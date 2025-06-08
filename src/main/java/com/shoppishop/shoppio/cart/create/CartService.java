package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.CartUtils;
import com.shoppishop.shoppio.cart.model.CartItemMapper;
import com.shoppishop.shoppio.cart.model.CreateCartRequest;
import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import com.shoppishop.shoppio.cart.model.entity.CartItemEntity;
import com.shoppishop.shoppio.cart.retrieve.RetrieveCartRepository;
import com.shoppishop.shoppio.catalogue.products.ProductEntity;
import com.shoppishop.shoppio.catalogue.products.ProductFetcher;
import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final RetrieveCartRepository retrieveCartRepository;
    private final CartItemMapper cartItemMapper;
    private final CartItemRepository cartItemRepository;
    private final ProductFetcher productFetcher;

    public BaseResponse createCart(CreateCartRequest request) {

        String lastCreatedCartId = getLastCreatedCartId();
        String generatedCartId = CodeGenerator.generateCartIdCode(lastCreatedCartId);

        if (request == null) {
            cartRepository.save(CartEntity.builder().cartId(generatedCartId).build());
            return CartUtils.buildCartCreateResponse(generatedCartId);
        }

        List<CartItemEntity> items =
                cartItemMapper.extractAndMapItemsToEntities(request, generatedCartId);
        CartEntity cartEntity = CartUtils.mapToCartEntity(items, generatedCartId);

        cartRepository.save(cartEntity);
        return CartUtils.buildCartCreateResponse(generatedCartId);
    }

    public BaseResponse deleteCart(String cartId) {
        cartRepository.deleteByCartId(cartId);
        return BaseResponse.builder().build();
    }

    public BaseResponse addProduct(String cartId, Long productId, Integer quantity) {
        CartEntity cartEntity = retrieveCartRepository.findCartById(cartId);

        if (cartEntity == null) {
            return CartUtils.buildCartNotFoundResponse();
        }

        Optional<CartItemEntity> existItem = CartUtils.findExistingItem(productId, cartEntity);

        if (existItem.isPresent()) {
            CartItemEntity cartItemEntity = CartUtils.addQuantity(quantity, existItem.get());
            cartItemRepository.save(cartItemEntity);
        } else {
            ProductEntity productEntity = productFetcher.fetchProduct(productId);
            cartItemRepository.save(CartUtils.buildCartItem(quantity, cartEntity, productEntity));
        }

        return BaseResponse.builder()
                .warnings(List.of(CartUtils.buildSuccessfullyAddedProductMessage(cartId)))
                .data(List.of(String.format("Product id: %d", productId)))
                .build();
    }

    public BaseResponse deleteProduct(String cartId, Long productId, Integer quantity) {
        CartEntity cartEntity = retrieveCartRepository.findCartById(cartId);

        if (cartEntity == null) {
            return CartUtils.buildCartNotFoundResponse();
        }

        Optional<CartItemEntity> existItem = CartUtils.findExistingItem(productId, cartEntity);

        if (existItem.isPresent()) {
            CartItemEntity cartItemEntity = existItem.get();
            if (cartItemEntity.getQuantity() <= quantity) {
                cartItemRepository.deleteItemByCartId(cartItemEntity.getId());
            } else {
                cartItemEntity = CartUtils.removeQuantity(quantity, existItem.get());
                cartItemRepository.save(cartItemEntity);
            }
        } else {
            throw BusinessException.builder()
                    .message(String.format("Product not found in cart: %s", cartId))
                    .build();
        }

        return BaseResponse.builder()
                .warnings(List.of(CartUtils.buildSuccessfullyProductRemovedMessage(cartId)))
                .build();
    }

    private String getLastCreatedCartId() {
        return cartRepository.findTopByOrderByCartIdDesc().map(CartEntity::getCartId).orElse(null);
    }
}
