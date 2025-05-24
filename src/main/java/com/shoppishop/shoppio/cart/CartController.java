package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.cart.create.CartService;
import com.shoppishop.shoppio.cart.model.CreateCartRequest;
import com.shoppishop.shoppio.models.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse> createCart(
            @Valid @RequestBody(required = false) CreateCartRequest request) {
        return ResponseEntity.ok(cartService.createCart(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BaseResponse> deleteCart(@RequestParam String cartId) {
        return ResponseEntity.ok(cartService.deleteCart(cartId));
    }

    //  @Deprecated
    @PostMapping("/{cartId}/product/add")
    public ResponseEntity<BaseResponse> addProduct(
            @PathVariable String cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.addProduct(cartId, productId, quantity));
    }

    @DeleteMapping("/{cartId}/product/delete")
    public ResponseEntity<BaseResponse> deleteProducts(
            @PathVariable String cartId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        return ResponseEntity.ok(cartService.deleteProduct(cartId, productId, quantity));
    }
}
