package com.Ecommerce.webApp.dto;

public class BookingRequest {

    private String productKeyword;
    private int quantity;
    private String customerName;

    // -- Getters & Setters --

    public String getProductKeyword() { return productKeyword; }
    public void setProductKeyword(String productKeyword) {
        this.productKeyword = productKeyword;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "productKeyword='" + productKeyword + '\'' +
                ", quantity=" + quantity +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
