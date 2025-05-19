package com.shoppishop.shoppio.cart.create;

import com.shoppishop.shoppio.models.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/cart")
@RestController
@RequiredArgsConstructor
public class CreateCartController {

  private final CreateCartService createCartService;

  @Operation(
      summary = "Create Cart",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Create Cart",
              content =
                  @Content(
                      mediaType = "application/json",
                      schema = @Schema(implementation = CreateCartRequest.class),
                      examples =
                          @ExampleObject(
                              value =
                                  """
                {
                  "items": [
                    {
                      "productId": 1,
                      "quantity": 1
                    },
                    {
                      "productId": 2,
                      "quantity": 5
                    }
                  ]
                }
            """))))
  @PostMapping("/create")
  public ResponseEntity<BaseResponse> createCart(@Valid @RequestBody CreateCartRequest request) {
    log.info(request.toString());
    System.out.println(request);
    return ResponseEntity.ok(createCartService.createCart());
  }
}
