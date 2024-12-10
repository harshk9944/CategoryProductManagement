package com.categoryproductmanagement.CategoryProductManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.categoryproductmanagement.CategoryProductManagement.entity.Category;
import com.categoryproductmanagement.CategoryProductManagement.entity.Product;
import com.categoryproductmanagement.CategoryProductManagement.repository.CatRepo;
import com.categoryproductmanagement.CategoryProductManagement.repository.ProRepo;

@Service
public class ProductService {

	@Autowired
	ProRepo repository;
	
	@Autowired
	CatRepo repository1;

	public ResponseEntity<Page<Product>> getPaginatedProducts(int page, int size) {
        Pageable pageable=PageRequest.of(page, size);
        if(pageable!=null) {
        return new ResponseEntity<>(repository.findAll(pageable),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		
		 Long categoryId = product.getCategory().getId();
	        
	        // Check if the category exists
	        Category category = repository1.findById(categoryId)
	                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
	        
	        product.setCategory(category); // Set the valid category
	        repository.save(product); // Save the product
	}

	public ResponseEntity<Optional<Product>> getProductById(long id) {
		Optional<Product> p=repository.findById(id);
		if(p!=null) {
			return new ResponseEntity<>(p,HttpStatus.OK);
		}
		return new ResponseEntity<Optional<Product>>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Product> updateProduct(long id, Product p) {
		Long categoryId=p.getCategory().getId();
		
		Optional<Product> existingProductOptional =repository.findById(id);

		if(existingProductOptional!=null) {
		Product p1=existingProductOptional.get();
		p1.setName(p.getName());
		p1.setPrice(p.getPrice());
		p1.setDescription(p.getDescription());
		p1.setCategory(p.getCategory());
		repository.save(p1);
		return new ResponseEntity<Product>(HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		
	}

	public ResponseEntity deleteProduct(long id) {

     Optional<Product> p=repository.findById(id);
     System.out.println(p);
     if(p.isEmpty()==false) {
    	 repository.deleteById(id);
    	 return new ResponseEntity("Deleted",HttpStatus.OK);
     }
     return new ResponseEntity("Enter the correct Id",HttpStatus.NOT_FOUND);
	}
}
