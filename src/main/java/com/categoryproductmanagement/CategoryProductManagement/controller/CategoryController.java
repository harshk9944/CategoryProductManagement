package com.categoryproductmanagement.CategoryProductManagement.controller;

import java.util.Objects;

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

import com.categoryproductmanagement.CategoryProductManagement.entity.Category;
import com.categoryproductmanagement.CategoryProductManagement.service.CategoryService;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<Page<Category>> getPaginatedCategories(@RequestParam(defaultValue = "0") int page) {
		int size = 10; // Fixed size
	    return new ResponseEntity<>(service.getPaginatedCategories(page, size),HttpStatus.OK);
	}
	
	 @PostMapping
	    public ResponseEntity createCategory(@RequestBody Category category) {
	        ResponseEntity response = service.createCategory(category);
	        return response;
	    }
	 
	 @GetMapping("{id}")
	 public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
	     Category category = service.getCategoryById(id);
	     
	     if (category == null) {
	         return ResponseEntity.notFound().build();  
	     } 
	     
	     return ResponseEntity.ok(category);  
	 }
	 
	 @PutMapping("{id}")
	 public ResponseEntity<Category> updateCategory(@PathVariable long id,@RequestBody Category c) {
		 ResponseEntity<Category> response=service.updateCategory(id,c);
		 return response;
	 }
	 
	 @DeleteMapping("{id}")
	 public ResponseEntity<Category> deleteById(@PathVariable long id) {
		 ResponseEntity<Category> response =service.deleteById(id);
		 return response;
	 }
}
