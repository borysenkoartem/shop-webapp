package com.epam.borysenko.entity.order;

import com.epam.borysenko.entity.product.Product;

import java.math.BigDecimal;

public final class OrderItem {

    private int id;
    private String orderId;
    private Product product;
    private BigDecimal price;
    private int count;

    public int getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
