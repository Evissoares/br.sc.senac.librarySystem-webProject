package br.sc.senac.librarySystem;

public class BookDTO {

	public static final BookDTO NUll_VALUE = new BookDTO(Long.valueOf(0), "", "", "", "");
	
	private Long bookCode;
	private String titleBook;
	private String authorBook;
	private String genreBook;
	private String releaseDate;
	
	BookDTO(Long bookCode, String titleBook, String authorBook,String genreBook, String releaseDate){
		this.bookCode = bookCode;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
		
	}

	public Long getBookCode() {
		return bookCode;
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
	
//	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//	public Date getDateOfBorrow() {
//		return dateOfBorrow;
//	}
}
