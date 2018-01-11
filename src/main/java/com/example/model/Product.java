package com.example.model;

import javax.persistence.*;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;
    private String name;
    private int price,quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
