package com.categoryproductmanagement.CategoryProductManagement.service;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.categoryproductmanagement.CategoryProductManagement.entity.Category;
import com.categoryproductmanagement.CategoryProductManagement.repository.CatRepo;

@Service
public class CategoryService {

	@Autowired
	private CatRepo repository;

	public Page<Category> getPaginatedCategories(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repository.findAll(pageable);
	}
	  public ResponseEntity createCategory(Category category) {
	       Category c= repository.save(category); 
	       
	       if(Objects.isNull(c)) {
		       return new ResponseEntity<>("Category not created",HttpStatus.NOT_FOUND);
 
	       }
	       return new ResponseEntity<>(HttpStatus.OK);
	    }
	  public Category getCategoryById(long id) {
	  Optional<Category> categoryOptional = repository.findById(id);  // findById instead of getById
	    return categoryOptional.orElse(null);
	}
	  
	public ResponseEntity<Category> updateCategory(long id, Category c) {
		 Optional<Category> existingcategoryOptional=repository.findById(id);
		 if(existingcategoryOptional!=null) {
			 Category existingCategory=existingcategoryOptional.get();
			 existingCategory.setName(c.getName());
			 existingCategory.setDescription(c.getDescription());
			  repository.save(existingCategory);
			  return new ResponseEntity<Category>(HttpStatus.OK);
		 }
		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	}
	public ResponseEntity<Category> deleteById(long id) {
		// TODO Auto-generated method stub
		
		Optional<Category> c=repository.findById(id);
		if(c.isEmpty()==false) {
			repository.deleteById(id);
		   return new ResponseEntity<Category>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
