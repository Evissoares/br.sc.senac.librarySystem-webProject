package br.sc.senac.librarysystem;

public class ReturnRequestLoanDTO {
	
	public static ReturnRequestLoanDTO NULL_VALUE = new ReturnRequestLoanDTO(Long.valueOf(0), Long.valueOf(0), "", Long.valueOf(0), "", "");
	
	private Long loanId;
	private Long readerId;
	private String readerName;
	private Long bookId;
	private String titleBook;
	private String authorBook;
	
	public ReturnRequestLoanDTO(Long emprestimoId, Long readerId, String readerName, Long bookId, String titleBook, String authorBook) {
		this.loanId = emprestimoId;
		this.readerId = readerId;
		this.readerName = readerName;
		this.bookId = bookId;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
	}

	public Long getLoanId() {
		return this.loanId;
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