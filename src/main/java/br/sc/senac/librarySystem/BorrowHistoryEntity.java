package br.sc.senac.librarySystem;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "History")
public class BorrowHistoryEntity extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 6518750173406685691L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long BorrowHistoryId;
	
	private ReaderEntity reader;
	
	private BookEntity book;
	
	protected BorrowHistoryEntity() {
		
	}
	
	public BorrowHistoryEntity(ReaderEntity reader, BookEntity book) {
		this.reader = reader;
		this.book = book;
	}

	public ReaderEntity getReader() {
		return reader;
	}

	public BookEntity getBook() {
		return book;
	}
}