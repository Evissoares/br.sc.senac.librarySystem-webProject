package br.sc.senac.librarysystem;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "solicitacaoDeEmprestimosEntity")
public class RequestLoanEntity implements Serializable {

	private static final long serialVersionUID = 6363660755806484916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	@OneToOne
	private ReaderEntity reader;

	@ManyToOne
	private BookEntity book;

	public RequestLoanEntity(ReaderEntity reader, BookEntity book) {
		this.reader = reader;
		this.book = book;
	}

	protected RequestLoanEntity() {

	}

	public Long getReaderId() {
		return reader.getReaderId();
	}

	public String getReaderName() {
		return reader.getReaderName();
	}

	public Long getBookId() {
		return book.getBookId();
	}

	public String getTitleBook() {
		return book.getTitleBook();
	}

	public String getAuthorBook() {
		return book.getAuthorBook();
	}

	public Long getLoanId() {
		return this.loanId;
	}

	public void setLoanId(Long emprestimoId) {
		this.loanId = emprestimoId;
	}
}
