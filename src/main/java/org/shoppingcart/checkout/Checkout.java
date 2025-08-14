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

    public double calculateTotalWithOffers(List<String> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        long appleCount = items.stream().filter(item -> item.equalsIgnoreCase("Apple")).count();
        long orangeCount = items.stream().filter(item -> item.equalsIgnoreCase("Orange")).count();

        // Buy One Get One Free on Apples
        long applesToPayFor = (appleCount / 2) + (appleCount % 2);

        // 3 for 2 on Oranges
        long orangesToPayFor = (orangeCount / 3) * 2 + (orangeCount % 3);

        return (applesToPayFor * APPLE_PRICE) + (orangesToPayFor * ORANGE_PRICE);
    }

}
