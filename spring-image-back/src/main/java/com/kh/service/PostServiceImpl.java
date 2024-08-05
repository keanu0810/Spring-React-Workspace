package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.dto.Post;
import com.kh.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostMapper postMapper;
	
	@Override
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insertImage() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void insertPost(Post post) {
		// TODO Auto-generated method stub
		
	}
	
	
}