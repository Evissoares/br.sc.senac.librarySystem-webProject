package br.sc.senac.librarysystem;

public class PesquisarBookDTO {
	
	private Long bookCode;
	
	public PesquisarBookDTO(Long bookCode) {
		this.bookCode = bookCode;
	}

	public Long getBookCode() {
		return bookCode;
	}
}