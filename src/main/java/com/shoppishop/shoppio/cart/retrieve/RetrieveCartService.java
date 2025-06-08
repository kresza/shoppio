package com.shoppishop.shoppio.cart.retrieve;

import com.shoppishop.shoppio.cart.model.dto.BasicCartDto;
import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.cart.model.dto.CartItemDto;
import com.shoppishop.shoppio.cart.model.entity.CartEntity;
import com.shoppishop.shoppio.cart.price.PromoCodes;
import com.shoppishop.shoppio.cart.price.TotalAmountEnrichment;
import com.shoppishop.shoppio.cart.price.TotalAmountPromotion;
import com.shoppishop.shoppio.catalogue.products.ProductDto;
import com.shoppishop.shoppio.catalogue.products.ProductEntity;
import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ErrorEnum;
import com.shoppishop.shoppio.models.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrieveCartService {

    private final RetrieveCartRepository retrieveCartRepository;
    private final TotalAmountEnrichment totalAmountEnrichment;
    private final TotalAmountPromotion totalAmountPromotion;

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
            List<CartDto> carts =
                    retrieveCartRepository.findAll().stream()
                            .map(this::mapCartToDtoAndEnrichWithTotalAmount)
                            .toList();
            return BaseResponse.builder().data(carts).build();
        } catch (Exception e) {
            throw BusinessException.builder()
                    .message(ErrorEnum.FETCHING_CARTS_ERROR.getMessage())
                    .code(ErrorEnum.FETCHING_CARTS_ERROR.getCode())
                    .build();
        }
    }

    public BaseResponse getCartById(String cartId, PromoCodes promotion) {
        try {
            List<ResponseMessage> warnings = new ArrayList<>();
            CartEntity cartEntity = retrieveCartRepository.findCartById(cartId);
            CartDto cartDto = mapCartToDtoAndEnrichWithTotalAmount(cartEntity);
            if (promotion != null) {
                totalAmountPromotion.addPromotion(cartDto, promotion.getPromoCode(), warnings);
            }
            return BaseResponse.builder().data(List.of(cartDto)).warnings(warnings).build();
        } catch (Exception e) {
            throw BusinessException.builder()
                    .message(
                            String.format(
                                    "%s%s", ErrorEnum.FETCHING_CART_ERROR.getMessage(), cartId))
                    .code(ErrorEnum.FETCHING_CART_ERROR.getCode())
                    .build();
        }
    }

    public CartDto mapCartToDtoAndEnrichWithTotalAmount(CartEntity cartEntity) {
        CartDto cartDto = mapToCartItemDto(cartEntity);

        return totalAmountEnrichment.calculateAndEnrichCartWithTotalAmount(cartDto);
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
}
