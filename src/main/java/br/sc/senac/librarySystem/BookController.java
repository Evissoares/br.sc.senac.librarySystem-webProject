package br.sc.senac.librarySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class BookController {

	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
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
		BookEntity bookEntity = BookController.toEntity(book);
		bookRepository.save(bookEntity);
		return bookEntity.getCodeBook();
	}
	
	BookDTO getBookFromRepository(Long codeBook) {
		Optional<BookEntity> bookEntity = bookRepository.findById(codeBook);
		if (bookEntity.isPresent()) {
			return BookController.toDTO(bookEntity.get());
		}
		return BookDTO.NUll_VALUE;
	}
	
	List<BookDTO> getAllBooksFromRepository() {
		List<BookDTO> selectedBooks = new ArrayList<>();
		Iterable<BookEntity> selectedEntities = bookRepository.findAll();
		for (BookEntity bookEntity : selectedEntities) {
			selectedBooks.add(BookController.toDTO(bookEntity));
		}
		return selectedBooks;
	}
	
	BookDTO removeBookFromRepository(Long codeBook) {
		Optional<BookEntity> selectedBookEntity = bookRepository.findById(codeBook);
		if (selectedBookEntity.isPresent()) {
			BookDTO removedBook = BookController.toDTO(selectedBookEntity.get());
			bookRepository.delete(selectedBookEntity.get());
			return removedBook;
		}
		return BookDTO.NUll_VALUE;
	}
	
	private static void updateBookEntityFromDTO(BookEntity oldBook, BookDTO newBook) {
		oldBook.setTitleBook(newBook.getTitleBook());
		oldBook.setAuthorBook(newBook.getAuthorBook());
		oldBook.setGenreBook(newBook.getGenreBook());
		oldBook.setReleaseDate(newBook.getReleaseDate());
	}
	
	BookDTO updateBookInRepository(BookDTO updateBook, Long codeBook) {
		Optional<BookEntity> selectedBook = bookRepository.findById(codeBook);
		if (selectedBook.isPresent()) {
			BookEntity bookForUpdate = selectedBook.get();
			BookDTO oldBook = BookController.toDTO(selectedBook.get());
			BookController.updateBookEntityFromDTO(bookForUpdate, updateBook);
			bookRepository.save(bookForUpdate);
			return oldBook;
		}
		return BookDTO.NUll_VALUE;
	}
}