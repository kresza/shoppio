package com.shoppishop.shoppio.catalogue;

import com.shoppishop.shoppio.catalogue.products.CatalogueService;
import com.shoppishop.shoppio.models.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/catalogue/products")
@RequiredArgsConstructor
@Tag(name = "Catalogue Controller", description = "Fetch all products or by category")
public class CatalogueController {

  private final CatalogueService catalogueService;

  @GetMapping("/all")
  @Operation(summary = "Return all products sorted by name alphabetically")
  public ResponseEntity<BaseResponse> getCatalogue() {
    return ResponseEntity.ok(catalogueService.getAllProducts());
  }

  @GetMapping("/by-category")
  @Operation(summary = "Return all available products found by category sorted by price ASC")
  public ResponseEntity<BaseResponse> getProductsByCategory(
          @RequestParam String category) {
    // implemented CategoryEnum instead of String to call endpoint faster without checking categories in db
    return ResponseEntity.ok(catalogueService.getProductsByCategory(category));
  }
}
