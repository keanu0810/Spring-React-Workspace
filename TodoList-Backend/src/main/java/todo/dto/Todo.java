package todo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// VO = dataBase 까지는 가지 않고 일기 전용 email 인증번호

// DTO = detaBase에 값을 연동해서 사용

// Entity = JPA DataBase Oracle에 테이블을 만들기 않아도 알아서 테이블 만들어주고
// 컬럼지정해주고 컬럼 값 설정


@ToString
@Setter
@Getter
public class Todo {
	private int todoNo; //할일 번호

	private String title;//할 일의 제목이자 내용
	private String isDone;//할 일을 롼료했는지 여부
	private int todoMemberNo;

}
