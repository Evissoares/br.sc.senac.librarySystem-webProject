package br.sc.senac.librarysystem;

public class OutputBookDTO {

	public static final OutputBookDTO NUll_VALUE = new OutputBookDTO(Long.valueOf(0), "", "", "", "", false);
	
	private Long bookId;
	private String titleBook;
	private String authorBook;
	private String genreBook;
	private String releaseDate;
	private Boolean lent;

	OutputBookDTO(Long bookId, String titleBook, String authorBook,String genreBook, String releaseDate, Boolean lent){
		this.bookId = bookId;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
		this.lent = lent;
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

	public Boolean getIsOnLoan() {
		return lent;
	}
}
