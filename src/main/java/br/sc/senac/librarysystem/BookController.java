package br.sc.senac.librarysystem;

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
	
	void addDefault() {
		BookEntity entidade = new BookEntity("Dragon Ball Z", "Akira Toryiama", "Ação", "Desconhecido");
		bookRepository.save(entidade);
		
		entidade = new BookEntity("Cavaleiros do Zodíaco", "Masami Kurumada", "Ação", "Desconhecido");
		bookRepository.save(entidade);
		
		entidade = new BookEntity("Dragon Ball GT", "Akira Toryiama", "Ação", "Desconhecido");
		bookRepository.save(entidade);
	}
	
	private static BookEntity toEntity(BookDTO bookDTO) {
		String titleBook = bookDTO.getTitleBook();
		String authorBook = bookDTO.getAuthorBook();
		String genreBook = bookDTO.getGenreBook();
		String releaseDate = bookDTO.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
	
	private static BookDTO toDTO(BookEntity bookEntity) {
		Long bookId = bookEntity.getBookId();
		String titleBook = bookEntity.getTitleBook();
		String authorBook = bookEntity.getAuthorBook();
		String genreBook = bookEntity.getGenreBook();
		String releaseDate = bookEntity.getReleaseDate();
		return new BookDTO(bookId, titleBook, authorBook, genreBook, releaseDate);
	}
	
	Long insertBookIntoRepository(BookDTO book) {
		BookEntity bookEntity = BookController.toEntity(book);
		bookRepository.save(bookEntity);
	    return bookEntity.getBookId();
	}
	
	BookDTO getBookFromRepository(Long bookCode) {
		Optional<BookEntity> bookEntity = bookRepository.findById(bookCode);
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
	
	BookDTO removeBookFromRepository(Long bookCode) {
		Optional<BookEntity> selectedBook = bookRepository.findById(bookCode);
		if (selectedBook.isPresent()) {
			BookDTO oldBook = toDTO(selectedBook.get());
			bookRepository.delete(selectedBook.get());
			return oldBook;
		}
		return BookDTO.NUll_VALUE;
	}
	
	/*private static void updateBookEntityFromDTO(BookEntity oldBook, BookDTO newBook) {
		oldBook.setTitleBook(newBook.getTitleBook());
		oldBook.setAuthorBook(newBook.getAuthorBook());
		oldBook.setGenreBook(newBook.getGenreBook());
		oldBook.setReleaseDate(newBook.getReleaseDate());
	}
	
	MensagensDeRetorno updateBookInRepository(BookDTO updateBook, Long codeBook) {
		Optional<BookEntity> selectedBook = bookRepository.findById(codeBook);
		if (selectedBook.isPresent()) {
			BookEntity bookForUpdate = selectedBook.get();
			BookController.updateBookEntityFromDTO(bookForUpdate, updateBook);
			bookRepository.save(bookForUpdate);
			return MensagensDeRetorno.LIVRO_ATUALIZADO;
		}
		return MensagensDeRetorno.LIVRO_NAO_ENCONTRADO;
	}*/
	
	MensagensDeRetorno<BookDTO> mensagemDeletado(BookDTO deletedBook) {
		return new MensagensDeRetorno<BookDTO>(deletedBook, MensagensDeRetorno.LIVRO_DELETADO);
	}
}