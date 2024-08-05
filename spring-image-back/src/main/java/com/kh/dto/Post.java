package com.kh.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id;
	private String title;
	private String content;
	private List<Image> images;

}