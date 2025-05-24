package com.shoppishop.shoppio.catalogue.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ErrorEnum;
import com.shoppishop.shoppio.models.ResponseMessage;
import com.shoppishop.shoppio.models.WarningEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogueService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    private static void addWarningIfResponseIsEmpty(
            List<ProductDto> products, BaseResponse baseResponse) {
        Optional.ofNullable(products)
                .filter(List::isEmpty)
                .ifPresent(
                        empty ->
                                baseResponse.addWarning(
                                        ResponseMessage.builder()
                                                .message(WarningEnum.CATALOGUE_EMPTY.getMessage())
                                                .code(WarningEnum.CATALOGUE_EMPTY.getCode())
                                                .build()));
    }

    public BaseResponse getAllProducts() {
        List<ProductEntity> products = productRepository.findAllByOrderByNameAsc();
        return buildResponse(mapProductsToDto(products));
    }

    public BaseResponse getProductsByCategory(String category) {
        try {
            List<ProductEntity> productEntities =
                    productRepository.findByCategoryNameOrderByPriceAsc(category);
            removeUnavailableProducts(productEntities);
            return buildResponse(mapProductsToDto(productEntities));
        } catch (Exception e) {
            throw BusinessException.builder()
                    .code(ErrorEnum.FETCHING_PRODUCTS_ERROR.getCode())
                    .message(ErrorEnum.FETCHING_PRODUCTS_ERROR.getMessage())
                    .build();
        }
    }

    private void removeUnavailableProducts(List<ProductEntity> products) {
        products.removeIf(product -> !product.isAvailable());
    }

    private List<ProductDto> mapProductsToDto(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(entity -> objectMapper.convertValue(entity, ProductDto.class))
                .toList();
    }

    private BaseResponse buildResponse(List<ProductDto> products) {
        BaseResponse baseResponse = BaseResponse.builder().data(products).build();
        addWarningIfResponseIsEmpty(products, baseResponse);
        return baseResponse;
    }
}
