package br.sc.senac.librarySystem;

import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {

	private final ReaderRepository readerRepository;
	private final BookRepository bookRepository;
	
	LibraryController(ReaderRepository readerRepository, BookRepository bookRepository){
		this.readerRepository = readerRepository;
		this.bookRepository = bookRepository;
	}
	
	BookEntity toEntity(BookDTO book) {
		String titleBook = book.getTitleBook();
		String authorBook = book.getAuthorBook();
		String genreBook = book.getGenreBook();
		String releaseDate = book.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
}
