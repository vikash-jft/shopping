package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subCategory")
public class SubCategory {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "childSubCategoryId")
    private SubCategory childSubCategory;

    @OneToMany(mappedBy = "childSubCategory")
    private List<SubCategory> subCategoryList;

    public SubCategory() {
    }

    public SubCategory(int id, String name, SubCategory childSubCategory, List<SubCategory> subCategoryList) {
        this.id = id;
        this.name = name;
        this.childSubCategory = childSubCategory;
        this.subCategoryList = subCategoryList;
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

    public SubCategory getChildSubCategory() {
        return childSubCategory;
    }

    public void setChildSubCategory(SubCategory childSubCategory) {
        this.childSubCategory = childSubCategory;
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
