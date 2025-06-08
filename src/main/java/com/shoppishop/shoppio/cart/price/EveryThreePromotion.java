package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.catalogue.products.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Component
class EveryThreePromotion {

    double addPromotionForEveryThreeProducts(CartDto cart) {
        List<ProductDto> allProducts = createAllProductsListSortedByPrice(cart);

         return getProductsPriceWithPromotion(allProducts);
    }

    private double getProductsPriceWithPromotion(
            List<ProductDto> allProducts) {
        int numberOfPromoProducts = allProducts.size() / 3;
        return IntStream.range(0, allProducts.size())
                .mapToDouble(
                        index ->
                                setPromotionToTheCheapestProducts(
                                        index, numberOfPromoProducts, allProducts))
                .sum();
    }

    private List<ProductDto> createAllProductsListSortedByPrice(CartDto cart) {
        return cart.getCartItems().stream()
                .map(item -> Collections.nCopies(item.getQuantity(), item.getProduct()))
                .flatMap(List::stream)
                .sorted(Comparator.comparing(ProductDto::getPrice))
                .toList();
    }

    private double setPromotionToTheCheapestProducts(
            int index, int numberOfPromoProducts, List<ProductDto> allProducts) {
        return index < numberOfPromoProducts ? 1.0 : allProducts.get(index).getPrice();
    }
}
