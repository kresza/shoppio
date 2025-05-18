package com.shoppishop.shoppio.dictionaries;

import com.shoppishop.shoppio.dictionaries.category.CategoryService;
import com.shoppishop.shoppio.models.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Dictionaries Controller")
public class DictionariesController {

  private final CategoryService categoryService;

  @GetMapping("/all")
  public ResponseEntity<BaseResponse> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }
}
