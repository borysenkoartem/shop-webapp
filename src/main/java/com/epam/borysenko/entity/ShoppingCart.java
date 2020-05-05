package com.epam.borysenko.entity;

import com.epam.borysenko.entity.product.Product;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {

    private Map<Product, Integer> shoppingCard;

    public ShoppingCart() {
        shoppingCard = new LinkedHashMap<>();
    }

    public void addProduct(Product product, Integer count) {
        Integer currentProductCount = shoppingCard.get(product);
        if (currentProductCount == null) {
            shoppingCard.put(product, count);
        } else {
            shoppingCard.merge(product, count, Integer::sum);
        }
    }

    public void removeProduct(Product product, int count) {
        Integer currentProductCount = shoppingCard.get(product);
        if (currentProductCount != null
                && currentProductCount > count) {
            shoppingCard.merge(product, count, (countAtShoppingCard, removeCount) -> countAtShoppingCard - removeCount);
        } else {
            shoppingCard.remove(product);
        }
    }

    public Map<Product, Integer> getShoppingCard() {
        return shoppingCard;
    }

    public BigDecimal getTotalCost() {

        return shoppingCard.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }

    public int getTotalCount() {
        return shoppingCard.values()
                .stream()
                .reduce(0, Integer::sum);
    }

    public void clear() {
        shoppingCard.clear();
    }
}
