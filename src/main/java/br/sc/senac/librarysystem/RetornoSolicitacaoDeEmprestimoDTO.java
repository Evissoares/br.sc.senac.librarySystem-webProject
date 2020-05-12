package br.sc.senac.librarysystem;

public class RetornoSolicitacaoDeEmprestimoDTO {
	
	private Long emprestimoId;
	private Long readerId;
	private String readerName;
	private Long bookId;
	private String titleBook;
	private String authorBook;
	
	public RetornoSolicitacaoDeEmprestimoDTO(Long emprestimoId, Long readerId, String readerName, Long bookId, String titleBook, String authorBook) {
		this.emprestimoId = emprestimoId;
		this.readerId = readerId;
		this.readerName = readerName;
		this.bookId = bookId;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
	}

	public Long getLong() {
		return this.emprestimoId;
	}
	
	public Long getReaderId() {
		return readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public Long getBookId() {
		return bookId;
	}

	public String getTitleBook() {
		return titleBook;
	}

	public String getAuthorBook() {
		return authorBook;
	}
}