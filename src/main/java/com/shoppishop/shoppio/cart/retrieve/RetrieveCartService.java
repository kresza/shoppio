package com.shoppishop.shoppio.cart.retrieve;

import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ErrorEnum;
import com.shoppishop.shoppio.products.ProductDto;
import com.shoppishop.shoppio.products.ProductEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetrieveCartService {

  private final RetrieveCartRepository retrieveCartRepository;

  public BaseResponse getAllCarts() {
    try {
      List<BasicCartDto> cartDto =
          retrieveCartRepository.findAll().stream().map(this::mapToCartDto).toList();
      return BaseResponse.builder().data(cartDto).build();
    } catch (Exception e) {
      throw BusinessException.builder()
          .message(ErrorEnum.FETCHING_CARTS_ERROR.getMessage())
          .code(ErrorEnum.FETCHING_CARTS_ERROR.getCode())
          .build();
    }
  }

  public BaseResponse getAllCartsDetails() {
    try {
      List<CartDto> cartDto =
          retrieveCartRepository.findAll().stream()
              .map(this::mapToCartItemDto)
              .map(this::calculateAndEnrichCartWithTotalAmount)
              .toList();
//      cartDto.forEach(this::calculateAndEnrichCartWithTotalAmount);
      return BaseResponse.builder().data(cartDto).build();
    } catch (Exception e) {
      throw BusinessException.builder()
          .message(ErrorEnum.FETCHING_CARTS_ERROR.getMessage())
          .code(ErrorEnum.FETCHING_CARTS_ERROR.getCode())
          .build();
    }
  }

  public BaseResponse getCartById(String cartId) {
    try {
      return BaseResponse.builder()
          .data(
              retrieveCartRepository.findCartById(cartId).stream()
                  .map(this::mapToCartItemDto)
                  .map(this::calculateAndEnrichCartWithTotalAmount)
                  .toList())
          .build();
    } catch (Exception e) {
      throw BusinessException.builder()
          .message(String.format("%s%s", ErrorEnum.FETCHING_CART_ERROR.getMessage(), cartId))
          .code(ErrorEnum.FETCHING_CART_ERROR.getCode())
          .build();
    }
  }

  public BaseResponse createCart() {

    return BaseResponse.builder().build();
  }

  private CartDto mapToCartItemDto(CartEntity cartEntity) {
    List<CartItemDto> cartItemDtoList =
        cartEntity.getCartItemEntity().stream()
            .map(
                cartItemEntity -> {
                  CartItemDto cartItemDto = new CartItemDto();
                  cartItemDto.setQuantity(cartItemEntity.getQuantity());

                  ProductEntity productEntity = cartItemEntity.getProductEntity();
                  ProductDto productDto = new ProductDto();
                  productDto.setName(productEntity.getName());
                  productDto.setPrice(productEntity.getPrice());
                  cartItemDto.setProduct(productDto);

                  return cartItemDto;
                })
            .toList();

    return CartDto.builder().cartId(cartEntity.getCartId()).cartItems(cartItemDtoList).build();
  }

  private BasicCartDto mapToCartDto(CartEntity cartEntity) {
    return BasicCartDto.builder()
        .cartId(cartEntity.getCartId())
        .createdAt(cartEntity.getCreatedAt())
        .build();
  }

  //  private void calculateAndEnrichCartWithTotalAmount(CartDto cartDto) {
  //    double totalAmount = calculateTotalAmount(cartDto);
  //    cartDto.setTotalAmount(roundAmountHalfUp(totalAmount));
  //  }

  private CartDto calculateAndEnrichCartWithTotalAmount(CartDto cartDto) {
    double totalAmount = calculateTotalAmount(cartDto);
    cartDto.setTotalAmount(roundAmountHalfUp(totalAmount));
    return cartDto;
  }

  private static double roundAmountHalfUp(double totalAmount) {
    return BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  private static double calculateTotalAmount(CartDto cartDto) {
    return cartDto.getCartItems().stream()
        .mapToDouble(cartItemDto -> cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity())
        .sum();
  }
}
