package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.cart.model.CreateCartRequest;
import com.shoppishop.shoppio.models.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CreateCartController {

  private final CartService cartService;

  @Deprecated(since = "New endpoint created")
  public ResponseEntity<BaseResponse> createCart(@Valid @RequestBody CreateCartRequest request) {
    return ResponseEntity.ok(cartService.createCart(request));
  }
}
