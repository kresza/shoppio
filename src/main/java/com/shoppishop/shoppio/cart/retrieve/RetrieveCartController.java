package com.shoppishop.shoppio.cart.retrieve;

import com.shoppishop.shoppio.cart.price.PromoCodes;
import com.shoppishop.shoppio.models.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retrieve/cart")
@RequiredArgsConstructor
@Tag(name = "Retrieve Cart Controller")
public class RetrieveCartController {

    private final RetrieveCartService retrieveCartService;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllCarts() {
        return ResponseEntity.ok(retrieveCartService.getAllCarts());
    }

    @GetMapping("/details/all")
    public ResponseEntity<BaseResponse> getAllCartsDetails() {
        return ResponseEntity.ok(retrieveCartService.getAllCartsDetails());
    }

    @GetMapping("/details/by-id")
    public ResponseEntity<BaseResponse> getCartDetailsById(
            @RequestParam String cartId, @RequestParam(required = false) PromoCodes promotion) {
        return ResponseEntity.ok(
                retrieveCartService.getCartById(
                        cartId, promotion));
    }
}
