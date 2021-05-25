package kr.ac.hansung.cse.exception;

public class UserNotFoundException extends RuntimeException {

	//객체 버전이 일치 해야 되기 때문에 , 버전을 맞춰주는 부분. 
	private static final long serialVersionUID = -5518945137487717586L;
	private Long userid;
	
	public UserNotFoundException(Long userid) {
		super();
		this.userid = userid;
	}
	
	public Long getUserid() {
		return userid;
	}
}
