package com.shoppingcart.checkout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shoppingcart.checkout.Checkout;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutOffersTest {
    public static final double APPLE_PRICE = 0.60;
    public static final double ORANGE_PRICE = 0.25;
    private Checkout checkout;
    @BeforeEach
    public void init() {
        checkout = new Checkout();
      }

    @Test
    public void shouldReturnZeroForEmptyCart() {
        List<String> items = Arrays.asList();
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.0, total);
    }


    @Test
    public void shouldApplyBOGOForApplesAndThreeForTwoForOranges() {
        double total = checkout.calculateTotalWithOffers(
                Arrays.asList("Apple", "Apple", "Orange", "Apple", "Orange", "Orange"));
        // Apples: 3 → pay for 2 → 1.20
        // Oranges: 3 → pay for 2 → 0.50
        assertEquals(1.70, total);
    }

    @Test
    public void shouldApplyBuyOneGetOneFreeOfferForTwoApples() {
        List<String> items = Arrays.asList("Apple", "Apple");
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.60, total); // 2 apples → pay for 1
    }

    @Test
    public void shouldCalculateTotalWithoutOffersForOneAppleAndOneOrange() {
        List<String> items = Arrays.asList("Apple", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // 1 Apple = 0.60, 1 Orange = 0.25 → total = 0.85
        assertEquals(0.85, total);
    }


    @Test
    public void shouldChargeExtraForLeftoverItemsAfterApplyingOffers() {
        List<String> items = Arrays.asList("Apple", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // Apples: 3 => pay for 2, Oranges: 4 => pay for 3
        assertEquals((2 * APPLE_PRICE) + (3 * ORANGE_PRICE), total);
    }
    @Test
    public void shouldApplyOffersForThreeApplesAndThreeOranges() {
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // Apples: 3 → pay for 2 → 1.20
        // Oranges: 3 → pay for 2 → 0.50
        assertEquals(1.70, total);
    }
    @Test
    public void shouldApplyThreeForTwoOfferForThreeOranges() {
        List<String> items = Arrays.asList("Orange", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // 3 oranges → pay for 2
        assertEquals(0.50, total);
    }


    @Test
    public void shouldCalculatePriceForSingleAppleWithoutOffer() {
        List<String> items = Arrays.asList("Apple");
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.60, total);
    }


    @Test
    public void shouldApplyOffersForExactMultiplesAndRemainders() {
        List<String> items = Arrays.asList(
                // 3 apples → pay for 2
                "Apple", "Apple", "Apple",
                // 4 oranges → pay for 3
                "Orange", "Orange", "Orange", "Orange"
        );
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals((2 * APPLE_PRICE) + (3 * ORANGE_PRICE), total);
    }

    @Test
    public void shouldHandleLargeCartWithOffersCorrectly() {
        List<String> items = new java.util.ArrayList<>();
        for (int i = 0; i < 1000; i++) items.add("Apple");
        for (int i = 0; i < 999; i++) items.add("Orange");

        double total = checkout.calculateTotalWithOffers(items);

        long applesToPay = (1000 / 2); // 500
        long orangesToPay = (999 / 3) * 2; // 666
        double expected = applesToPay * APPLE_PRICE + orangesToPay * ORANGE_PRICE;
        assertEquals(expected, total);
    }


}
