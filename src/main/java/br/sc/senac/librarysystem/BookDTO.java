package br.sc.senac.librarysystem;

public class BookDTO {

	public static final BookDTO NUll_VALUE = new BookDTO(Long.valueOf(0), "", "", "", "", false);
	
	private Long bookId;
	private String titleBook;
	private String authorBook;
	private String genreBook;
	private String releaseDate;
	private Boolean isEmprestado;
	
	BookDTO(Long bookId, String titleBook, String authorBook,String genreBook, String releaseDate, Boolean isEmprestado){
		this.bookId = bookId;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
		this.isEmprestado = isEmprestado;
	}
	
	public Long getBookId() {
		return bookId;
	}
	
	public String getTitleBook() {
		return titleBook;
	}

	public String getAuthorBook() {
		return authorBook;
	}

	public String getGenreBook() {
		return genreBook;
	}

	public String getReleaseDate() {
		return releaseDate;
	}
	
	public Boolean getStatusEmprestimo() {
		return this.isEmprestado;
	}
}