package com.codewithatharv.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithatharv.blog.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
