package br.sc.senac.librarySystem;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "borrowedHistory")
public class BorrowedRecordsEntity implements Serializable {

	private static final long serialVersionUID = -2738018363258062316L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long borrowedId;
	
	@ManyToOne
	private ReaderEntity reader;
	
	@ManyToOne
	private BookEntity book;
	
	private LocalDateTime dateOfBorrowed;
	
	public BorrowedRecordsEntity(ReaderEntity reader, BookEntity book) {
		this.reader = reader;
		this.book = book;
	}
	
	protected BorrowedRecordsEntity() {
		
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

	public LocalDateTime getDateOfBorrowed() {
		return dateOfBorrowed;
	}
	
	public void setDateOfBorrowed(LocalDateTime dateOfBorrowed) {
		this.dateOfBorrowed = dateOfBorrowed;
	}
}