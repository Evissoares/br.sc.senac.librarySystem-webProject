package br.sc.senac.librarysystem;

public class RequestLoanDTO {

	private Long bookId;
	private Long readerId;
	
	public RequestLoanDTO(Long bookId, Long readerId) {
		this.bookId = bookId;
		this.readerId = readerId;
	}
	public Long getBookId() {
		return bookId;
	}
	
	public Long getReaderId() {
		return readerId;
	}
	
}
