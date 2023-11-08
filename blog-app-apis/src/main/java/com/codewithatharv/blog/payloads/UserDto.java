package com.codewithatharv.blog.payloads;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//created for transferring the data
@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;	
    
    @NotEmpty
    @Size(min=4,message="Username must be min of 4 characters")
	private String name;	
    
    @Email(message="Email address is not valid")
	private String email;	
    
    @NotEmpty
    @Size(min=3,max=10,message="Password must be minimum of 3 chars and maximum of 10 chars!!")//  @Pattern(regexp=)
	private String password;	
    
    @NotEmpty
	private String about;
    
private Set<RoleDto> roles = new HashSet<RoleDto>();
	
	
	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
	
}
