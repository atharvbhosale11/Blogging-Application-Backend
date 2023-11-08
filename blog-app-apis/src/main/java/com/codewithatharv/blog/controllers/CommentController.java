package com.codewithatharv.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithatharv.blog.entities.Comment;
import com.codewithatharv.blog.payloads.ApiResponse;
import com.codewithatharv.blog.payloads.CommentDto;
import com.codewithatharv.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
	{
		CommentDto createComment=this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted succesfully",true),HttpStatus.OK);
	}
}
