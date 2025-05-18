package com.shoppishop.shoppio.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WarningEnum {
    
    CATALOGUE_EMPTY(1001L, "No products found in catalogue");
    
    private final Long code;
    private final String message;
}
