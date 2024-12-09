package com.categoryproductmanagement.CategoryProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.categoryproductmanagement.CategoryProductManagement.entity.Category;

@Repository
public interface CatRepo extends JpaRepository<Category, Long>{

}

