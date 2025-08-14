package org.shoppingcart.checkout;

import java.util.List;

public class Checkout {
    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    public double calculateTotal(List<String> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (String item : items) {
            if ("Apple".equalsIgnoreCase(item)) {
                total += APPLE_PRICE;
            } else if ("Orange".equalsIgnoreCase(item)) {
                total += ORANGE_PRICE;
            }
        }
        return total;
    }
}
