package com.categoryproductmanagement.CategoryProductManagement.service;

import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	  public String createCategory(Category category) {
	       Category c= repository.save(category); 
	       String msg="Data added successfully";
	       if(Objects.isNull(c)) {
	    	   msg="Data is not added";
	    	   return msg;
	       }
	       return msg;
	    }
	  public Category getCategoryById(long id) {
	  Optional<Category> categoryOptional = repository.findById(id);  // findById instead of getById
	    return categoryOptional.orElse(null);
	}
	  
	public Category updateCategory(long id, Category c) {
		 Optional<Category> existingcategoryOptional=repository.findById(id);
		 if(existingcategoryOptional!=null) {
			 Category existingCategory=existingcategoryOptional.get();
			 existingCategory.setName(c.getName());
			 existingCategory.setDescription(c.getDescription());
			 return repository.save(existingCategory);
		 }
		return null;
	}
	public String deleteById(long id) {
		// TODO Auto-generated method stub
		Optional<Category> c=repository.findById(id);
		if(c.isEmpty()==false) {
		   repository.deleteById(id);
		   return "Deleted";
		}
		return "Enter the correct Id";
	}
}
