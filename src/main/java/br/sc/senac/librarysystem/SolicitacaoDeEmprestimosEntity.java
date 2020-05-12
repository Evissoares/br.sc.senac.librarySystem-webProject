package br.sc.senac.librarysystem;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "solicitacaoDeEmprestimosEntity")
public class SolicitacaoDeEmprestimosEntity implements Serializable {

	private static final long serialVersionUID = 6363660755806484916L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emprestimoId;
	
	@ManyToOne
	private ReaderEntity reader;
	
	@ManyToOne
	private BookEntity book;
	
	public SolicitacaoDeEmprestimosEntity(ReaderEntity reader, BookEntity book) {
		this.reader = reader;
		this.book = book;
	}
	
	protected SolicitacaoDeEmprestimosEntity() {
		
	}

	public Long getEmprestimoId() {
		return this.emprestimoId;
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

	public void setEmprestimoId(Long emprestimoId) {
		this.emprestimoId = emprestimoId;
	}
}
