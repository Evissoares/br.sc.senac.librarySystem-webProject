package br.sc.senac.librarySystem;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "borrowedHistory")
public class BorrowedHistoryEntity implements Serializable {

	private static final long serialVersionUID = -2738018363258062316L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long historyId;
	
	@ManyToOne
	private ReaderEntity reader;
	
	@ManyToOne
	private BookEntity book;
	
	private LocalDateTime dateOfBorrow;
	
	public BorrowedHistoryEntity(ReaderEntity reader, BookEntity book) {
		this.reader = reader;
		this.book = book;
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

	public LocalDateTime getDateOfBorrow() {
		return dateOfBorrow;
	}
	
	public void setDateOfBorrow(LocalDateTime dateOfBorrow) {
		this.dateOfBorrow = dateOfBorrow;
	}
}