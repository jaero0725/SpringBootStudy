package kr.ac.hansung.exception;

public class NotFoundException extends RuntimeException {
	
	
	private static final long serialVersionUID = 8795546082074185666L;
	
	private Long Id;
	
	public NotFoundException(Long Id) {
		this.Id = Id;
	}
	
	public long getId() {
		return Id;
	}
}
