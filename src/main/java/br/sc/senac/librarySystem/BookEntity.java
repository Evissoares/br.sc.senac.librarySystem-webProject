package br.sc.senac.librarySystem;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "bookEntity")
public class BookEntity implements Serializable {

	private static final long serialVersionUID = -2940296870496603755L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codeBook;
	
	private String titleBook;
	
	private String authorBook;
	
	private String genreBook;
	
	private String releaseDate;

	public BookEntity(String titleBook, String authorBook, String genreBook, String releaseDate) {
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
	}
	
	protected BookEntity() {
		
	}

	@Override
	public String toString() {
		return "Título: " + titleBook +  "Autor: " + authorBook +  "Gênero: " + genreBook
				+ "Data de lançamento: " + releaseDate;
	}

	public String getTitleBook() {
		return titleBook;
	}

	public void setTitleBook(String titleBook) {
		if(titleBook != null) {
		this.titleBook = titleBook;
		}
	}

	public String getAuthorBook() {
		return authorBook;
	}

	public void setAuthorBook(String authorBook) {
		if(authorBook != null) {
		this.authorBook = authorBook;
		}
	}

	public String getGenreBook() {
		return genreBook;
	}

	public void setGenreBook(String genreBook) {
		if(genreBook != null) {
		this.genreBook = genreBook;
		}
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		if(releaseDate != null) {
		this.releaseDate = releaseDate;
		}
	}
	
	public Long getCodeBook() {
		return this.codeBook;
	}
}
