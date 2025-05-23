package com.shoppishop.shoppio.cart;

import com.shoppishop.shoppio.cart.create.CartService;
import com.shoppishop.shoppio.cart.create.CreateCartRequest;
import com.shoppishop.shoppio.cart.retrieve.RetrieveCartService;
import com.shoppishop.shoppio.cart.update.UpdateCartRequest;
import com.shoppishop.shoppio.models.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
  private final RetrieveCartService retrieveCartService;

  @PostMapping("/create")
  public ResponseEntity<BaseResponse> createCart(@Valid @RequestBody CreateCartRequest request) {
    log.info(request.toString());
    System.out.println(request);
    return ResponseEntity.ok(cartService.createCart(request));
  }

  @DeleteMapping("/delete")
  public ResponseEntity<BaseResponse> deleteCart(@RequestParam String cartId) {
    return ResponseEntity.ok(cartService.deleteCart(cartId));
  }

  @Deprecated
  @PostMapping("/product/add")
  public ResponseEntity<BaseResponse> addProduct(
      @RequestParam String cartId, @RequestBody UpdateCartRequest request) {
    return ResponseEntity.ok(cartService.deleteCart(cartId));
  }
}
