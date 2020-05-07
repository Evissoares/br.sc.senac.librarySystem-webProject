package br.sc.senac.librarySystem;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BorrowedHistoryDTO {

	private Long historyId;
	private ReaderEntity reader;
	private BookEntity book;
	private LocalDateTime dateOfBorrow;
	
	public BorrowedHistoryDTO(Long historyId, ReaderEntity reader, BookEntity book, LocalDateTime dateOfBorrow) {
		this.historyId = historyId;
		this.reader = reader;
		this.book = book;
		this.dateOfBorrow = dateOfBorrow;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public ReaderEntity getReader() {
		return reader;
	}

	public BookEntity getBook() {
		return book;
	}

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public LocalDateTime getDateOfBorrow() {
		return dateOfBorrow;
	}
}
