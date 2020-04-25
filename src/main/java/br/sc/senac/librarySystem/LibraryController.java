package br.sc.senac.librarySystem;

import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {

	private final ReaderRepository readerRepository;
	private final BookRepository bookRepository;
	
	LibraryController(ReaderRepository readerRepository, BookRepository bookRepository) {
		this.readerRepository = readerRepository;
		this.bookRepository = bookRepository;
	}
	
	private static BookEntity toEntity(BookDTO bookDTO) {
		String titleBook = bookDTO.getTitleBook();
		String authorBook = bookDTO.getAuthorBook();
		String genreBook = bookDTO.getGenreBook();
		String releaseDate = bookDTO.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
	
	private static BookDTO toDTO(BookEntity bookEntity) {
		Long codeBook = bookEntity.getCodeBook();
		String titleBook = bookEntity.getTitleBook();
		String authorBook = bookEntity.getAuthorBook();
		String genreBook = bookEntity.getGenreBook();
		String releaseDate = bookEntity.getReleaseDate();
		return new BookDTO(codeBook, titleBook, authorBook, genreBook, releaseDate);
	}
	
	Long insertBookIntoRepository(BookDTO book) {
		BookEntity bookEntity = LibraryController.toEntity(book);
		bookRepository.save(bookEntity);
		return bookEntity.getCodeBook();
	}
	
	BookDTO getBookFromRepository(Long codeBook) {
		Optional<BookEntity> bookEntity = bookRepository.findById(codeBook);
		if(bookEntity.isPresent()) {
			return LibraryController.toDTO(bookEntity.get());
		}
		return BookDTO.NUll_VALUE;
	}	
}
