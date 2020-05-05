package com.epam.borysenko.entity.order;

import com.epam.borysenko.annotation.Column;

import java.sql.Timestamp;
import java.util.List;

public class Order {

    private String id;
    private String address;
    @Column(value = "phone_number")
    private String phoneNumber;
    @Column(value = "delivery")
    private String delivery;
    @Column(value = "status")
    private String status;
    @Column(value = "payment")
    private String payment;
    private String description;
    private Timestamp created;
    @Column(value = "user_id")
    private Integer userId;
    private List<OrderItem> orderItemList;


    public Order( String id, Integer userId) {
        this.id = id;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Order() {
    }

}