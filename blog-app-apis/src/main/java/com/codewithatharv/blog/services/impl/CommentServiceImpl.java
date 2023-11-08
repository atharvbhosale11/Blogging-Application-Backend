package com.codewithatharv.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithatharv.blog.entities.Comment;
import com.codewithatharv.blog.entities.Post;
import com.codewithatharv.blog.exception.ResourceNotFoundException;
import com.codewithatharv.blog.payloads.CommentDto;
import com.codewithatharv.blog.repositories.CommentRepo;
import com.codewithatharv.blog.repositories.PostRepo;
import com.codewithatharv.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post id",postId));
				
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentID) {
		Comment comm=this.commentRepo.findById(commentID).orElseThrow(()->new ResourceNotFoundException("Comment","comment id",commentID));
		
		this.commentRepo.delete(comm);
		
	}

}
