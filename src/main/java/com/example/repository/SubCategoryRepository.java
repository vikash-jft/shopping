package com.example.repository;

import com.example.model.Category;
import com.example.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findByCategory(Category category);
}
