package com.codewithatharv.blog.services;

import java.util.List;

import com.codewithatharv.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto UpdateCategory(CategoryDto categoryDto,Integer categoryId);
	

	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//get All
	List<CategoryDto> getCategories();

}
