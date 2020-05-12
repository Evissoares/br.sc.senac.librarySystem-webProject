package br.sc.senac.librarysystem;

public class SolicitacaoDeEmprestimosDTO {

	private Long bookId;
	private Long readerId;
	
	public SolicitacaoDeEmprestimosDTO(Long bookId, Long readerId) {
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
