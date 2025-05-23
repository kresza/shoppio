package com.shoppishop.shoppio.cart.create;

import lombok.experimental.UtilityClass;

@UtilityClass
class CodeGenerator {

  private static final String PREFIX = "C_";

  public String generateCartIdCode(String lastCreatedCartId) {
    int cartNumber = 1;

    if (lastCreatedCartId != null && lastCreatedCartId.length() == 8) {
      String lastCartNumber = lastCreatedCartId.substring(2);
      cartNumber = Integer.parseInt(lastCartNumber) + 1;
    }

    return formatCode(cartNumber);
  }

  private String formatCode(int cartNumber) {
    return String.format("%s%06d", PREFIX, cartNumber);
  }
}
