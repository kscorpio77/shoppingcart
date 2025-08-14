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


}
