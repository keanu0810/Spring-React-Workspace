package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.dto.NaverUser;
import com.kh.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	/*
	 * 로그인했을 때 
	 * 로그인 값이 있으면 왜 total : 1
	 * 로그인 값이 없으면 왜 total : 0
	 * ==>  Preparing: SELECT id, email, nickname, name, profile_image FROM naver_user WHERE id= ? AND password = ?
	 * Parameters: kFPP1dD2ZLXceZoRAUHiUt5n98G4bBmf_IQ2-7OXWso(String), aaa(String)
	 *      Total: 1
	 *      Total: 1 = 로그인 조회했을 때 존재하는 컬럼이 1개 발견 1
	 *      
	 *        맞지 않는 아이디 비밀번호를 조회했을 때 컬럼이 0개 발견 0
	 * */
	@PostMapping("/login")
	// login  @RequestParam -> @RequestBody Map<String, String> loginData
	public ResponseEntity<String> login(@RequestParam("id") String id, @RequestParam("password") String password) {
		NaverUser user = loginService.login(id, password);
		if (user != null) { //유저정보가 존재하면 null 아닐 것
			return ResponseEntity.ok("로그인 성공");
		} else { // 유저정보가 존재하지 않아 null로 전달
			// ResponseEntity.status = DB나 어떤 값에 대한 결과 상태
			// HttpStatus = GET POST와 같은 메서드 기능이 잘 동작했느냐
			// UNAUTHORIZED = 인증실패 주로 로그인실패
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
								.body("로그인 실패");
		}
		
	}

}









