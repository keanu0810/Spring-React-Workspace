package com.kh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 공공데이터 api 를 이용한 api url 주소값 한 번 더 확인

//  http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty

// 공공데이터 주소  http://apis.data.go.kr/    -> env 로 80 처리를 한 주소
// 내 컴퓨터  주소 http://localhost:포트번호/    -> react env 로 80 처리를 한 주소


@RequestMapping("/B552584/ArpltnInforInqireSvc") //공공데이터에서 대기오염서비스 공통 주소
// 만약에 대기오염서비스가 아니라 수질오염서비스 주소를 이용해야한다면
// 리퀘스트매핑에 /수질오염서비스api 를 작성해주면 됨 @RequestMapping("/수질오염서비스로이동하기")
@RestController
public class APISController {
	// 3번
	@GetMapping("/getCtprvnRltmMesureDnsty") //시도별 실시간 측정 정보 조회 api 주소
	public String get실시간측정정보() {
		return "측정결과전달하기";
	}
	// 1번과 2번 return 측정 결과 전달하기
	// 4번과 5번 void system.out.println("측정 결과 전달하기")
	// 1. GetMApping 주소로 측정소별 실시간 측정정보 조회
	@GetMapping("/getMsrstnAcctoRltmMesureDnsty")
	public String get주소로측정하기() {
		return "측정결과 전달하기";
	}
	// 2. 통합대기환경지수 나쁨이상 측정소 목록조회
	@GetMapping("/getUnityAirEnvrnIdexSnstiveAboveMsrstnList")  //  \특수문자 특수문자를 글자취급
	public String get나쁨측정소목록조회() {
		return "측정결과 전달하기";
	}
	// 4. 대기질 예보통보 조회
	@GetMapping("getMinuDustFrcstDspth")
	public void 예보통보조회() {
		System.out.println("측정결과 전달하기");
	}
	// 5. 미세먼지 주간예보 조회
	@GetMapping("getMinuDustWeekFrcstDspth")
	public void 주간예보조회() {
		 System.out.println("측정결과 전달하기");
	}
}