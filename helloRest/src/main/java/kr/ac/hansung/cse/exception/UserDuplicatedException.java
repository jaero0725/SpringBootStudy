package kr.ac.hansung.cse.exception;

public class UserDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = -3312437843437623808L;

	private String username;

	public UserDuplicatedException(String username) {
		super();
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
}
