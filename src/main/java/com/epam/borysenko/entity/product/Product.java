package com.epam.borysenko.entity.product;

import com.epam.borysenko.annotation.Column;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private Integer id;
    private String name;
    private String description;
    @Column(value = "image_link")
    private String imageLink;
    private BigDecimal price;
    private String producer;
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Product [id=%s, name=%s, description=%s, imageLink=%s, price=%s, producer=%s, category=%s]",
                getId(), name, description, imageLink, price, producer, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(imageLink, product.imageLink) &&
                Objects.equals(price, product.price) &&
                Objects.equals(producer, product.producer) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, imageLink, price, producer, category);
    }
}
