package com.example.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "category")
    private List<SubCategory> subCategories;

    public Category() {
    }

    public Category(String name, List<SubCategory> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

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

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
