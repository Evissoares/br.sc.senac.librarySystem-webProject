package br.sc.senac.librarySystem;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BorrowedRecordsDTO {
	
	public static final BorrowedRecordsDTO NULL_VALUE  = new BorrowedRecordsDTO(Long.valueOf(0), null, null, null);

	private Long borrowedId;
	private ReaderEntity reader;
	private BookEntity book;
	private LocalDateTime dateOfBorrowed;
	
	public BorrowedRecordsDTO(Long borrowedId, ReaderEntity reader, BookEntity book, LocalDateTime dateOfBorrowed) {
		this.borrowedId = borrowedId;
		this.reader = reader;
		this.book = book;
		this.dateOfBorrowed = dateOfBorrowed;
	}

	public Long getBorrowedId() {
		return borrowedId;
	}

	public ReaderEntity getReader() {
		return reader;
	}

	public BookEntity getBook() {
		return book;
	}

	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	public LocalDateTime getDateOfBorrowed() {
		return dateOfBorrowed;
	}
}
