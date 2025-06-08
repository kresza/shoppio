package com.shoppishop.shoppio.cart.price;

import com.shoppishop.shoppio.cart.model.dto.CartDto;
import com.shoppishop.shoppio.models.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TotalAmountPromotion {
    private static final String ADDED_PROMOTION_MSG = "Added promotion: %s";
    private final EveryThreePromotion everyThreePromotion;
    private final TenPercentPromotion tenPercentPromotion;
    private final SecondHalfPricePromotion secondHalfPricePromotion;
    private final TotalAmountEnrichment totalAmountEnrichment;

    public void addPromotion(CartDto cart, String promoCode, List<ResponseMessage> warnings) {
        var foundPromotion = findPromotion(promoCode);
        if (foundPromotion == null || promoCode == null) {
            return;
        }
        double totalAmount = cart.getTotalAmount();
        switch (foundPromotion) {
            case ALL_10_PROMOTION -> {
                totalAmount = tenPercentPromotion.addTenPercentTotalAmountPromotion(cart);
                addedPromotionInfo(warnings, PromoCodes.ALL_10_PROMOTION.getDescription());
            }
            case EVERY3_PROMOTION -> {
                totalAmount = everyThreePromotion.addPromotionForEveryThreeProducts(cart);
                addedPromotionInfo(warnings, PromoCodes.EVERY3_PROMOTION.getDescription());
            }
            case SECOND_HALF_PRICE_PROMOTION -> {
                totalAmount = secondHalfPricePromotion.addSecondHalfPricePromo(cart);
                addedPromotionInfo(
                        warnings, PromoCodes.SECOND_HALF_PRICE_PROMOTION.getDescription());
            }
        }

        totalAmount = totalAmountEnrichment.roundAmountHalfUp(totalAmount);
        cart.setTotalAmount(totalAmount);
    }

    private PromoCodes findPromotion(String promoCode) {
        return Arrays.stream(PromoCodes.values())
                .filter(promoCodes -> promoCode.equals(promoCodes.getPromoCode()))
                .findFirst()
                .orElse(null);
    }

    private void addedPromotionInfo(List<ResponseMessage> warnings, String description) {
        warnings.add(
                ResponseMessage.builder()
                        .message(String.format(ADDED_PROMOTION_MSG, description))
                        .build());
    }
}
