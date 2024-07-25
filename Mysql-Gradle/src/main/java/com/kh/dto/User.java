package com.kh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자 자동생성
@AllArgsConstructor//필수 생성자 자동생성
public class User {
	private int id;
	private String name;
	private String email;
	
	
}
