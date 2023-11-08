package com.codewithatharv.blog.services;

import java.util.List;

import com.codewithatharv.blog.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);
	
	UserDto createuser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllusers();
	void deleteUser(int userId);
	
	
}
