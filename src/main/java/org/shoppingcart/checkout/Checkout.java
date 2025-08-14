package org.shoppingcart.checkout;

import java.util.List;
import java.util.function.Predicate;

public class Checkout {
    private static final double APPLE_PRICE = 0.60;
    private static final double ORANGE_PRICE = 0.25;

    Predicate<String> isApple = item-> item.equalsIgnoreCase("Apple");
    Predicate<String> isOrange = item-> item.equalsIgnoreCase("Orange");

    public double calculateTotal(List<String> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }

        return items.stream()
                .mapToDouble(item -> {
                    if ("Apple".equalsIgnoreCase(item)) {
                        return APPLE_PRICE;
                    } else if ("Orange".equalsIgnoreCase(item)) {
                        return ORANGE_PRICE;
                    }
                    return 0.0;
                })
                .sum();
    }
        public double calculateTotalWithOffers(List<String> items) {
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        long appleCount = items.stream().filter(isApple).count();
        long orangeCount = items.stream().filter(isOrange).count();

        // Buy One Get One Free on Apples
        long applesToPayFor = (appleCount / 2) + (appleCount % 2);

        // 3 for 2 on Oranges
        long orangesToPayFor = (orangeCount / 3) * 2 + (orangeCount % 3);

        return (applesToPayFor * APPLE_PRICE) + (orangesToPayFor * ORANGE_PRICE);
    }

}
