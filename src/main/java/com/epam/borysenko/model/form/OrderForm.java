package com.epam.borysenko.model.form;

public class OrderForm {

    private String phone;
    private String address;
    private int deliveryId;
    private int paymentId;

    public OrderForm(String phone, String address, int deliveryId, int paymentId) {
        this.phone = phone;
        this.address = address;
        this.deliveryId = deliveryId;
        this.paymentId = paymentId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}
