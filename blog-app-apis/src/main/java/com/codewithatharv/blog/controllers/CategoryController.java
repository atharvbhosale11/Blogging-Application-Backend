package com.codewithatharv.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithatharv.blog.payloads.ApiResponse;
import com.codewithatharv.blog.payloads.CategoryDto; 
import com.codewithatharv.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categDto)
	{
		CategoryDto createCategory = this.categoryService.createCategory(categDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categDto,@PathVariable Integer catId)
	{
		CategoryDto updateCategory = this.categoryService.UpdateCategory(categDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
	{
			this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCateogory(@PathVariable Integer categoryId)
	{
		CategoryDto categorydto=(this.categoryService.getCategory(categoryId));
		return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCateories()
	{
		List<CategoryDto> categories=(this.categoryService.getCategories());
		return ResponseEntity.ok(categories);
	}
	
}
