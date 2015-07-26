package com.nilhcem.henripotier.core.cart;

import com.nilhcem.henripotier.model.CommercialOffer;
import com.nilhcem.henripotier.model.Type;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BestOfferCalculatorTest {

    @Test
    public void should_compute_minus_discount() {
        // Given
        CommercialOffer offer = new CommercialOffer(Type.MINUS, 42f, 0f);

        // When
        float result = BestOfferCalculator.INSTANCE$.computeDiscount(100f, offer);

        // Then
        assertThat(result).isEqualTo(42f);
    }

    @Test
    public void should_compute_percentage_discount() {
        // Given
        CommercialOffer offer = new CommercialOffer(Type.PERCENTAGE, 20f, 0f);

        // When
        float result = BestOfferCalculator.INSTANCE$.computeDiscount(50f, offer);

        // Then
        assertThat(result).isEqualTo(10f);
    }

    @Test
    public void should_compute_slice_discount_with_0_slices() {
        // Given
        CommercialOffer offer = new CommercialOffer(Type.SLICE, 5f, 10f);

        // When
        float result = BestOfferCalculator.INSTANCE$.computeDiscount(5f, offer);

        // Then
        assertThat(result).isEqualTo(0f);
    }

    @Test
    public void should_compute_slice_discount_with_1_slice() {
        // Given
        CommercialOffer offer = new CommercialOffer(Type.SLICE, 5f, 10f);

        // When
        float result = BestOfferCalculator.INSTANCE$.computeDiscount(18f, offer);

        // Then
        assertThat(result).isEqualTo(5f);
    }

    @Test
    public void should_compute_slice_discount_with_3_slices() {
        // Given
        CommercialOffer offer = new CommercialOffer(Type.SLICE, 5f, 10f);

        // When
        float result = BestOfferCalculator.INSTANCE$.computeDiscount(30f, offer);

        // Then
        assertThat(result).isEqualTo(15f);
    }

    @Test
    public void should_return_best_commercial_offer() {
        // Given
        CommercialOffer offer1 = new CommercialOffer(Type.MINUS, 10f, 0f);
        CommercialOffer offer2 = new CommercialOffer(Type.MINUS, 30f, 0f);
        CommercialOffer offer3 = new CommercialOffer(Type.MINUS, 20f, 0f);

        // When
        CommercialOffer bestOffer = BestOfferCalculator.INSTANCE$.getBestOffer(100f, Arrays.asList(offer1, offer2, offer3));

        // Then
        assertThat(bestOffer).isSameAs(offer2);
    }
}
