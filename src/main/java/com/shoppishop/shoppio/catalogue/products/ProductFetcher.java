package com.shoppishop.shoppio.catalogue.products;

import com.shoppishop.shoppio.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.shoppishop.shoppio.cart.CartUtils.PRODUCT_NOT_FOUND_ERROR;

@Component
@RequiredArgsConstructor
public class ProductFetcher {

    private final ProductRepository productRepository;

    public ProductEntity fetchProduct(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                BusinessException.builder()
                                        .message(String.format(PRODUCT_NOT_FOUND_ERROR, id))
                                        .build());
    }


    public void deleteAllProductsFromCart(String cartId){
        productRepository.deleteAllByCartId(cartId);
    }
}
