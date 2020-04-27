package br.sc.senac.librarySystem;

public class BorrowHistoryDTO {
	
	private Long readerId;
	private String readerName;
	private Long codeBook;
	private String titleBook;
	private String dateAndTimeOfBorrow;
	
	public BorrowHistoryDTO(Long readerId, String readerName, Long codeBook, String titleBook, String dateAndTimeOfBorrow) {
		this.dateAndTimeOfBorrow = dateAndTimeOfBorrow;
		this.readerId = readerId;
		this.readerName = readerName;
		this.codeBook = codeBook;
		this.titleBook = titleBook;
	}

	public Long getReaderId() {
		return readerId;
	}

	public String getReaderName() {
		return readerName;
	}

	public Long getCodeBook() {
		return codeBook;
	}

	public String getTitleBook() {
		return titleBook;
	}

	public String getDateAndTimeOfBorrow() {
		return dateAndTimeOfBorrow;
	}
}
