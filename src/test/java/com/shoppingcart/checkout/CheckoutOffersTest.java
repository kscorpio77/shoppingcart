package com.shoppingcart.checkout;

import org.junit.jupiter.api.Test;
import org.shoppingcart.checkout.Checkout;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutOffersTest {

    @Test
    public void shouldApplyBOGOForApplesAndThreeForTwoForOranges() {
        Checkout checkout = new Checkout();
        double total = checkout.calculateTotalWithOffers(
                Arrays.asList("Apple", "Apple", "Orange", "Apple", "Orange", "Orange"));
        // Apples: 3 → pay for 2 → 1.20
        // Oranges: 3 → pay for 2 → 0.50
        assertEquals(1.70, total);
    }

}
