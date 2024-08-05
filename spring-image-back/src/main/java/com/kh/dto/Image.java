package com.kh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	private int id;
	private int postId;
	private String imageUrl;
	
}
