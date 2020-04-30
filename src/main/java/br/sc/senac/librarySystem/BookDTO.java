package br.sc.senac.librarySystem;

public class BookDTO {

	public static final BookDTO NUll_VALUE = new BookDTO(Long.valueOf(0), "", "", "", "");
	
	private Long codeBook;
	private String titleBook;
	private String authorBook;
	private String genreBook;
	private String releaseDate;
	
	BookDTO(Long codeBook, String titleBook, String authorBook,String genreBook, String releaseDate){
		this.codeBook = codeBook;
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
	}

	public Long getCodeBook() {
		return codeBook;
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
}
