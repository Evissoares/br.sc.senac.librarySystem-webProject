package br.sc.senac.librarySystem;

import java.util.ArrayList;
import java.util.List;
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
	
	private static BookEntity toBookEntity(BookDTO bookDTO) {
		String titleBook = bookDTO.getTitleBook();
		String authorBook = bookDTO.getAuthorBook();
		String genreBook = bookDTO.getGenreBook();
		String releaseDate = bookDTO.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
	
	private static BookDTO toBookDTO(BookEntity bookEntity) {
		Long codeBook = bookEntity.getCodeBook();
		String titleBook = bookEntity.getTitleBook();
		String authorBook = bookEntity.getAuthorBook();
		String genreBook = bookEntity.getGenreBook();
		String releaseDate = bookEntity.getReleaseDate();
		return new BookDTO(codeBook, titleBook, authorBook, genreBook, releaseDate);
	}
	
	Long insertBookIntoRepository(BookDTO book) {
		BookEntity bookEntity = LibraryController.toBookEntity(book);
		bookRepository.save(bookEntity);
		return bookEntity.getCodeBook();
	}
	
	BookDTO getBookFromRepository(Long codeBook) {
		Optional<BookEntity> bookEntity = bookRepository.findById(codeBook);
		if(bookEntity.isPresent()) {
			return LibraryController.toBookDTO(bookEntity.get());
		}
		return BookDTO.NUll_VALUE;
	}
	
	List<BookDTO> getAllBooksFromRepository(){
		List<BookDTO> selectedBooks = new ArrayList<>();
		Iterable<BookEntity> selectedEntities = bookRepository.findAll();
		for(BookEntity bookEntity : selectedEntities) {
			selectedBooks.add(LibraryController.toBookDTO(bookEntity));
		}
		return selectedBooks;
	}
	
	BookDTO removeBookFromRepository(Long codeBook) {
		Optional<BookEntity> selectedBookEntity = bookRepository.findById(codeBook);
		if(selectedBookEntity.isPresent()) {
			BookDTO removedBook = LibraryController.toBookDTO(selectedBookEntity.get());
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
		if(selectedBook.isPresent()) {
			BookEntity bookForUpdate = selectedBook.get();
			BookDTO oldBook = LibraryController.toBookDTO(bookForUpdate);
			LibraryController.updateBookEntityFromDTO(bookForUpdate, updateBook);
			bookRepository.save(bookForUpdate);
			return oldBook;
		}
		return BookDTO.NUll_VALUE;
	}

	private static ReaderEntity toReaderEntity(ReaderDTO reader) {
		String readerName = reader.getReaderName();
		Integer readerAge = reader.getReaderAge();
		return new ReaderEntity(readerName, readerAge);
	}
	
	private static ReaderDTO toReaderDTO(ReaderEntity reader) {
		Long readerId = reader.getReaderId();
		String readerName = reader.getEntityReaderName();
		Integer readerAge = reader.getEntityReaderAge();
		return new ReaderDTO(readerId, readerName, readerAge);
	}
}
