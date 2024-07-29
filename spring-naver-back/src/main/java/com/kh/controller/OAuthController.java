package com.kh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping("/api")
public class OAuthController {

	/*
	 * @Value = 환경설정 appication.propperties나 config.propperties에 작성한 키 이름을 가져오고 키에 긴
	 * 값을 가져오는 어노테이션
	 */
	// appication.propperties.client-id = 7VaPvskrBGAEo5IaPiDl
	@Value("${naver.client-id}")
	private String clientId; // client-id = 7VaPvskrBGAEo5IaPiDl

	// appication.propperties.client-secret=ikGlklQFcZ
	@Value("${naver.client-secret}")
	private String clientSecret; // client-secret=ikGlklQFcZ

	// appication.propperties.redirect-url=http://localhost:3000/naverLogin
	@Value("${naver.redirect-uri}")
	private String redirectUri; // redirect-url=http://localhost:3000/naverLogin

	// appication.propperties.state
	@Value("${naver.state}")
	private String state;

	/*
	 * app.get('/naverlogin', function (req, res) { api_url =
	 * 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' +
	 * client_id + '&redirect_uri=' + redirectURI + '&state=' + state;
	 * res.writeHead(200, {'Content-Type': 'text/html;charset=utf-8'});
	 * res.end("<a href='"+ api_url +
	 * "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>"
	 * ); });
	 */
	@GetMapping("/naverLogin")
	public String naverLogin() {
		String api_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
		return "<a href='" + api_url
				+ "'><img height='50' src='http://static.nid.naver.com/oauth/small_g_in.PNG'/></a>";
	}
	/*
	 url 에 {}= 변수명 표시가 없으면 @RequestParam 이나 @RequestBody
	 url 에 {}= 변수명 표시가 있으면 @PathVariable {} 안에 있는 변수명 값을 집어 넣는다
	 
	 * */
	@GetMapping("/callback")
	public String callback(@RequestParam String code, @RequestParam String state) {
		// 네이버에서 로그인을 성공하고 성공했을 때 받는 도장
		// 1. client-id	   = 어디회사에 들어왔는지 
		// 2. clientSecret = 회사 들어오기 위한 비밀번호
		// 3. redirectUri  = 들어오기 위한 심사를 무사히 완료 했으면 내보내는 위치로 전달
		// 4. code 		   = 네이버로 부터 무사히 들어왔다는 인증코드 받음
		// 5. state 	   = CSRF 공격을 방지하기 위해 사용
		String api_url = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
			     + clientId + "&client_secret=" + clientSecret + "&redirect_uri=" + redirectUri + "&code=" + code + "&state=" + state;
	    
		//RestTemplate = HTTP 메서드 GET POST PUT DELETE 통해서 
		//데이터를 JSON 형식으로 데이터를 처리
		RestTemplate restTemplate = new RestTemplate();
		// api_Url 줏로 응답받은 결과를 String = 문자로 가져와서 사용하겠다.
		String 응답결과 = restTemplate.getForObject(api_url, String.class);
		return 응답결과;
	}
	
	 
}
