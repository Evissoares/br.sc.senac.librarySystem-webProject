package br.sc.senac.librarySystem;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BorrowHistoryDTO {
	
	private Long readerId;
	private String readerName;
	private Long codeBook;
	private String titleBook;
	private Date dateAndTimeOfBorrow;
	
	public BorrowHistoryDTO(Long readerId, String readerName, Long codeBook,
			String titleBook, Date dateAndTimeOfBorrow) {
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

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public Date getDateAndTimeOfBorrow() {
		return dateAndTimeOfBorrow;
	}
	
}
