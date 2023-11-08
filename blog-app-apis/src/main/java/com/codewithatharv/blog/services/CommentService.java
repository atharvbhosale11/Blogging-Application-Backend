package com.codewithatharv.blog.services;

import com.codewithatharv.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentID);
	
	

}
