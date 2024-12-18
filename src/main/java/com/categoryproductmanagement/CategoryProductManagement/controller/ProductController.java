package com.categoryproductmanagement.CategoryProductManagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.categoryproductmanagement.CategoryProductManagement.entity.Product;
import com.categoryproductmanagement.CategoryProductManagement.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping
    public ResponseEntity<Page<Product>> getPaginatedProducts(@RequestParam(defaultValue ="0") int page){
		int size=10;
		return service.getPaginatedProducts(page,size);
	}
	
	 @PostMapping
	    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
	        try {
	            service.saveProduct(product);
	            return ResponseEntity.status(HttpStatus.CREATED).body("Product saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save product: " + e.getMessage());
	        }
	    }
	 
	 @GetMapping("{id}")
	 public ResponseEntity<Optional<Product>> getProductById(@PathVariable long id) {
		 ResponseEntity<Optional<Product>> p=service.getProductById(id);
		 return p;
	 }
	 
	 @PutMapping("{id}")
	 public ResponseEntity updateProduct(@PathVariable long id,@RequestBody Product p) {
		 ResponseEntity msg=service.updateProduct(id,p);
		 return msg;
	 }
	 
	 @DeleteMapping("{id}")
	 public ResponseEntity deleteProduct(@PathVariable long id) {
		 ResponseEntity msg=service.deleteProduct(id);
		 return msg;
	 }
}
