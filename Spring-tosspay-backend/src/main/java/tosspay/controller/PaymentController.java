package tosspay.controller;

import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController //html 파일 url 주소값으로 연동
@RequestMapping("/confirm")
public class PaymentController {
	//application.properties에 설정 키이름을 가져오기 위해 value
	@Value("${widgetSecretKey}") // 특정한 키이름을 외부나 다른곳에서 가져와 사용할 때는 ${키이름} 작성
	private String widgetSecretKey;
	
	@Value("${apiSecretKey}")
	private String apiSecretKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private String encodeSecretKey(String secretKey) {
		return "Basic" + new String(Base64.getEncoder().encode((secretKey + ":").getBytes()));
				
	}
	//widget 이라는 주소로 결제정보가 들어오면 결제확인창구로 넘겨주는 것 결제정보와 결제하고하는 사용자의 비밀번호
	// 위젯 -> 페이먼트 브랜드페이 결제랑 결제 방식이 살짝 달라서 widget
	@PostMapping("/widget")
	public ResponseEntity<?> confirmWidget(@RequestBody Map<String, String> requestBody) {
		return confirmPayment(requestBody, encodeSecretKey(widgetSecretKey));
	}
	
	//[atment 라는 주소로 결제 정보가 들어오면 결제 확인 창구로 넘겨주는 것 결제정보와 결제하고자 하는 사용자의 비밀번호
	@PostMapping("/widget")
	public ResponseEntity<?> confirmPayment(@RequestBody Map<String, String> requestBody) {
		return confirmPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	@PostMapping("/brandpay")
	public ResponseEntity<?> confirmBrandpay(@RequestBody Map<String, String> requestBody) {
		return confirmBrandPayPayment(requestBody, encodeSecretKey(apiSecretKey));
	}
	
	
	private ResponseEntity<?> confirmPayment(Map<String, String> requestBody, String encodedKey){
		//  fetch("https://api.tosspayments.com/v1/payments/confirm", {
		String url = "https://api.tosspayments.com/v1/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//      Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey 을 위에서 encodedKey 작성해줬음
		//		  "Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
	
	private ResponseEntity<?> confirmBrandPayPayment(Map<String, String> requestBody, String encodedKey){
		//  fetch("https://api.tosspayments.com/v1/brandpay/payments/confirm", {
		String url = "https://api.tosspayments.com/v1/brandpay/payments/confirm";
		HttpHeaders headers = new HttpHeaders();
		//      Authorization: encryptedApiSecretKey,
		headers.set("Authorization", encodedKey);//encryptedApiSecretKey 을 위에서 encodedKey 작성해줬음
		//		  "Content-Type": "application/json",
		headers.set("Content-Type", "application/json");
		
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);
		
		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);
		return new ResponseEntity<>(response.getBody(), response.getStatusCode());
	}
}

/***** 
 Entity
 
 
 
 
 
 
 ResponseEntity = 사용자가 요청한 응답을 개발자가 다시 사용자한테 전달할 떄 사용
 
 
 
 
 
 HttpCntity : HTTP 요청 또는 응답의 본문(body)과 헤더(headers)를 포함하는 객체
 Http : 요청을 보낼 때 본문과 헤더를 설정하고자 할 때 사용
 본문(body) : 실제 전송될 데이터 ex) 아이디 비밀번호 작성한 글 등
 헤더(headers) : HTTp 헤더 정보를 포함 ex) 글자인지 이미지인지 동영상인지 .. 어떤 파일이 들어오는 것인가> 누가보내나?
 HttpCntity<문자열이면 문자열, 숫자면 숫자, 모르겠으면 비워드기> abc = new HttpCntity<비워져있어도 상관 ㅇ벗음> ("요청본문", headers);

ResponseEntity ( Response = 응답 /Http 상속받아서 Http 기능에 응답에 대한 기능을 추가로 설정한 Entity 
			: HttpEntity를 상속받아, Http 응답에 대한 추가적인 정보를 제공 샅애코드를 포함하고 있어서
			 클라이얻트(사용자)에게 응답을 보낼 때 사용
ReponseEntity(String이면 String, Integer면 Integer 여러값이면 ? , 므르겠으면 비워두기?> = new ResponseEntity<>(응답본문",headers); 

ReQuestEntity ( Response = 요청 /Http 상속받아서 Http 기능에 요청에 대한 기능을 추가로 설정한 Entity
 			: HttpEntity 를 상속해 ,Http 요청에 대한 추가적인 정보를 제공
 			URI 와 HTTP메서드 (GET, POST, PUT, DELETE)를 포함하고 있어, 서버로 요청을 보낼 때 주로 사용
RequestEntity String이면 String , Integer면 Integer, 여러값이면 ? , 모르겠으면 비워두기> req = new RequsetEntity<>("요청본문",headers);
RequestEntity String req> = new RequsetEntity<>("요청본문",headers, HttpMethod, post, url);
HTTP = 사용자한테 응답 /사용자한테 요청
차이점 요약하기
	클래스  				상속관계 				주요사용목적			    	추가정보
	HTTPEntity			기본 클래스				HTTP요청/응답 본문과 헤더 포함   	상태코드없음(성공여부)
	ResponseEntitiy		HttpEntity 상속		HTTP 응답 반환					상태코드포함(성공여부)
	REqyestEntity		HttpEntity 상속     	HTTP 요청 전송					URI와 HTTP 메서드 포함


HTTP : 웹이서 데이터를 전송하기 위한 전송수단



URI = 주소값과 식별값이 들어있을 이 안에 URL이 들어있음 

URL = URI의 한 종류로 주소이름

URM = 고유한 이름
 
 * */