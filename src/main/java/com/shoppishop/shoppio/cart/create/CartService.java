package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.retrieve.CartEntity;
import com.shoppishop.shoppio.cart.retrieve.CartItemEntity;
import com.shoppishop.shoppio.cart.update.UpdateCartRequest;
import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.products.ProductEntity;
import com.shoppishop.shoppio.products.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private static final String PRODUCT_NOT_FOUND_ERROR = "Product with given id: %d not found";
  private final CartRepository cartRepository;
  private final ProductRepository productRepository;

  public BaseResponse createCart(CreateCartRequest request) {

    String lastCreatedCartId = getLastCreatedCartId();
    String generatedCartId = CodeGenerator.generateCartIdCode(lastCreatedCartId);

    List<CartItemEntity> items = extractAndMapItemsToEntities(request, generatedCartId);

    CartEntity cartEntity = mapToCartEntity(items, generatedCartId);
    cartRepository.save(cartEntity);

    // TODO build response
    return BaseResponse.builder().build();
  }

  public BaseResponse deleteCart(String cartId){
    cartRepository.deleteByCartId(cartId);
    return BaseResponse.builder().build();
  }

  // TODO retrieve existing products and add quantity if the same or create new
  public BaseResponse addProduct(String cartId, UpdateCartRequest updateCartRequest){
    cartRepository.deleteByCartId(cartId);
    return BaseResponse.builder().build();
  }

  private CartEntity mapToCartEntity(List<CartItemEntity> items, String generatedCartId) {
    return CartEntity.builder().cartItemEntity(items).cartId(generatedCartId).build();
  }

  private List<CartItemEntity> extractAndMapItemsToEntities(
      CreateCartRequest request, String generatedCartId) {
    return request.getItems().stream()
        .map(item -> mapCartItemToEntity(item, generatedCartId))
        .toList();
  }

  private CartItemEntity mapCartItemToEntity(CreateCartItem item, String generatedCartId) {
    ProductEntity productEntity = fetchProduct(item.getProductId());

    return CartItemEntity.builder()
        .cartId(generatedCartId)
        .quantity(item.getQuantity())
        .productEntity(productEntity)
        .build();
  }

  private String getLastCreatedCartId() {
    return cartRepository.findTopByOrderByCartIdDesc().map(CartEntity::getCartId).orElse(null);
  }

  private ProductEntity fetchProduct(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(
            () ->
                BusinessException.builder()
                    .message(String.format(PRODUCT_NOT_FOUND_ERROR, id))
                    .build());
  }
}
