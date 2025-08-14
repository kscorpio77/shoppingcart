package com.shoppingcart.checkout;

import org.junit.jupiter.api.Test;
import org.shoppingcart.checkout.Checkout;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutOffersTest {

    @Test
    public void shouldReturnZeroForEmptyCart() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList();
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.0, total);
    }


    @Test
    public void shouldApplyBOGOForApplesAndThreeForTwoForOranges() {
        Checkout checkout = new Checkout();
        double total = checkout.calculateTotalWithOffers(
                Arrays.asList("Apple", "Apple", "Orange", "Apple", "Orange", "Orange"));
        // Apples: 3 → pay for 2 → 1.20
        // Oranges: 3 → pay for 2 → 0.50
        assertEquals(1.70, total);
    }

    @Test
    public void shouldApplyBuyOneGetOneFreeOfferForTwoApples() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Apple", "Apple");
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.60, total); // 2 apples → pay for 1
    }

    @Test
    public void shouldCalculateTotalWithoutOffersForOneAppleAndOneOrange() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Apple", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // 1 Apple = 0.60, 1 Orange = 0.25 → total = 0.85
        assertEquals(0.85, total);
    }


    @Test
    public void shouldChargeExtraForLeftoverItemsAfterApplyingOffers() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Apple", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // Apples: 3 => pay for 2, Oranges: 4 => pay for 3
        assertEquals((2 * 0.60) + (3 * 0.25), total);
    }
    @Test
    public void shouldApplyOffersForThreeApplesAndThreeOranges() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // Apples: 3 → pay for 2 → 1.20
        // Oranges: 3 → pay for 2 → 0.50
        assertEquals(1.70, total);
    }
    @Test
    public void shouldApplyThreeForTwoOfferForThreeOranges() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Orange", "Orange", "Orange");
        double total = checkout.calculateTotalWithOffers(items);
        // 3 oranges → pay for 2
        assertEquals(0.50, total);
    }


    @Test
    public void shouldCalculatePriceForSingleAppleWithoutOffer() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList("Apple");
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals(0.60, total);
    }


    @Test
    public void shouldApplyOffersForExactMultiplesAndRemainders() {
        Checkout checkout = new Checkout();
        List<String> items = Arrays.asList(
                // 3 apples → pay for 2
                "Apple", "Apple", "Apple",
                // 4 oranges → pay for 3
                "Orange", "Orange", "Orange", "Orange"
        );
        double total = checkout.calculateTotalWithOffers(items);
        assertEquals((2 * 0.60) + (3 * 0.25), total);
    }

    @Test
    public void shouldHandleLargeCartWithOffersCorrectly() {
        Checkout checkout = new Checkout();
        List<String> items = new java.util.ArrayList<>();
        for (int i = 0; i < 1000; i++) items.add("Apple");
        for (int i = 0; i < 999; i++) items.add("Orange");

        double total = checkout.calculateTotalWithOffers(items);

        long applesToPay = (1000 / 2); // 500
        long orangesToPay = (999 / 3) * 2; // 666
        double expected = applesToPay * 0.60 + orangesToPay * 0.25;
        assertEquals(expected, total);
    }



}
