package com.shoppingcart.checkout;

import org.junit.jupiter.api.Test;
import org.shoppingcart.checkout.Checkout;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest {
    Checkout checkout = new Checkout();

    @Test
    public void shouldReturnZeroForEmptyCart() {
        List<String> items = Arrays.asList();
        double total = checkout.calculateTotal(items);
        assertEquals(0.0, total);
    }

    @Test
    public void shouldCalculateTotalWithoutOffers() {
        List<String> items = Arrays.asList("Apple", "Apple", "Orange", "Apple");
        double total = checkout.calculateTotal(items);
        assertEquals(2.05, total);
    }

    @Test
    public void shouldIgnoreInvalidItems() {
        List<String> items = Arrays.asList("Apple", "Banana", "Orange");
        double total = checkout.calculateTotal(items);
        assertEquals(0.85, total); // Apple + Orange only
    }


    @Test
    public void shouldHandleCaseInsensitiveItems() {
        List<String> items = Arrays.asList("apple", "APPLE", "Apple");
        double total = checkout.calculateTotal(items);
        assertEquals(1.80, total, 0.001); // 3 apples
    }

}
