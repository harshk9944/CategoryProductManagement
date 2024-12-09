package com.categoryproductmanagement.CategoryProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.categoryproductmanagement.CategoryProductManagement.entity.Product;

@Repository
public interface ProRepo extends JpaRepository<Product, Long>{

}
