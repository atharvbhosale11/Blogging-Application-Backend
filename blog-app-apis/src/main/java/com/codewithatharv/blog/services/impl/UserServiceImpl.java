package com.codewithatharv.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithatharv.blog.config.AppConstants;
import com.codewithatharv.blog.entities.Role;
import com.codewithatharv.blog.entities.User;
import com.codewithatharv.blog.payloads.UserDto;
import com.codewithatharv.blog.repositories.RoleRepo;
import com.codewithatharv.blog.repositories.UserRepo;
import com.codewithatharv.blog.services.UserService;
import com.codewithatharv.blog.exception.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMappper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createuser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.usertoDto(savedUser);
		
		
		//return null;
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.usertoDto(updatedUser);
		
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllusers() {
		// TODO Auto-generated method stub
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.usertoDto(user)).collect(Collectors.toList());
		
		
		return userDtos;
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(user);
		

	}
	
	private User dtoToUser(UserDto user)
	{
//		User user1=new User();
//		user1.setId(user.getId());
//		user1.setName(user.getName());
//		user1.setEmail(user.getEmail());
//		user1.setAbout(user.getAbout());
//		user1.setPassword(user.getPassword());
		
		User user1=this.modelMappper.map(user, User.class); 
		return user1;
		
	}
	
	public UserDto usertoDto(User user)
	{
//		UserDto userdto=new UserDto();
//		userdto.setName(user.getName());
//		userdto.setId(user.getId());
//		userdto.setEmail(user.getEmail());
//		userdto.setAbout(user.getAbout());
//		userdto.setPassword(user.getPassword());
		
		UserDto userDto=this.modelMappper.map(user, UserDto.class);
		return userDto;
	}
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = this.modelMappper.map(userDto, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = this.userRepo.save(user);

		return this.modelMappper.map(newUser, UserDto.class);	}

}
