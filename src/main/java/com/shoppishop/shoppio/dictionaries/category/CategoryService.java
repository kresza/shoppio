package com.shoppishop.shoppio.dictionaries.category;

import com.shoppishop.shoppio.exceptions.BusinessException;
import com.shoppishop.shoppio.models.BaseResponse;
import com.shoppishop.shoppio.models.ErrorEnum;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public BaseResponse getAllCategories() {
    try {
      return BaseResponse.builder().data(fetchAndMapCategories()).build();
    } catch (Exception e) {
      throw BusinessException.builder()
          .message(ErrorEnum.FETCHING_CATEGORIES_ERROR.getMessage())
          .code(ErrorEnum.FETCHING_CATEGORIES_ERROR.getCode())
          .build();
    }
  }

  private List<CategoryDto> fetchAndMapCategories() {
    return categoryRepository.findAll().stream().map(this::mapCategoryToDto).toList();
  }

  private CategoryDto mapCategoryToDto(CategoryEntity categoryEntity) {
    return CategoryDto.builder().name(categoryEntity.getName()).build();
  }
}
