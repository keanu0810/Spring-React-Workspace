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
	private String imageUrl;
	// id 와 createAt mysql 자동으로 숫자와 날짜 생성을 해주기때문에
	// mapper.xml에 작성하지 않음
	private String createdAt; //게시판에 작성한 글과 이미지가 mysql에 들어온 시간

}