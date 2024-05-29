package com.example.finalproject;

public class Orders {
    private String name;
    private Double price;
    private Integer quantity;
    private Double totalPrice;
    public Orders(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        totalPrice = price*quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
