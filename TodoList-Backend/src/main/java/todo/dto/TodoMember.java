package todo.dto;

import lombok.*;

@ToString
@Getter
@Setter
public class TodoMember {
	private int todoMemberNo;
	private String id;
	
	private String pw;
	private String name;
}
