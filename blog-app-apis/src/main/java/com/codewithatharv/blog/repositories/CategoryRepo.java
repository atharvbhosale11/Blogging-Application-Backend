package com.codewithatharv.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codewithatharv.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

	
	
}
