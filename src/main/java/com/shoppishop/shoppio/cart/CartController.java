package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.models.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Cart Controller")
public class CartController {

  private final CartService cartService;

  @GetMapping("/all")
  public ResponseEntity<BaseResponse> getAllCarts(){
    return ResponseEntity.ok(cartService.getAllCarts());
  }

  @GetMapping("/details/all")
  public ResponseEntity<BaseResponse> getAllCartsDetails() {
    return ResponseEntity.ok(cartService.getAllCartsDetails());
  }

  @GetMapping("/details/by-id")
  public ResponseEntity<BaseResponse> getCartDetailsById(@RequestParam String cartId) {
    return ResponseEntity.ok(cartService.getCartById(cartId));
  }
}
