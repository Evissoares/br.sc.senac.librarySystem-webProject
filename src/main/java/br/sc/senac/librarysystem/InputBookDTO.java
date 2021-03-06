package br.sc.senac.librarysystem;

public class InputBookDTO {
	
	private String titleBook;
	private String authorBook;
	private String genreBook;
	private String releaseDate;
	
	InputBookDTO(String titleBook, String authorBook,String genreBook, String releaseDate) {
		this.titleBook = titleBook;
		this.authorBook = authorBook;
		this.genreBook = genreBook;
		this.releaseDate = releaseDate;
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