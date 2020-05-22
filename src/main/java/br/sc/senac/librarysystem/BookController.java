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
	
<<<<<<< HEAD
	public void saveBookEntity(BookEntity book) {
=======
	public void salvarEntidadeLivro(BookEntity book) {
>>>>>>> 3426bb01b4a93e1324c3de73cbfe8745af8bda1c
		bookRepository.save(book);
	}
	
	void addDefault() {
		BookEntity entidade = new BookEntity("Dragon Ball Z", "Akira Toryiama", "Ação", "Desconhecido");
		bookRepository.save(entidade);
		
		entidade = new BookEntity("Cavaleiros do Zodíaco", "Masami Kurumada", "Ação", "Desconhecido");
		bookRepository.save(entidade);
		
		entidade = new BookEntity("Dragon Ball GT", "Akira Toryiama", "Ação", "Desconhecido");
		bookRepository.save(entidade);
	}
	
	private static BookEntity toEntity(InputBookDTO bookDTO) {
		String titleBook = bookDTO.getTitleBook();
		String authorBook = bookDTO.getAuthorBook();
		String genreBook = bookDTO.getGenreBook();
		String releaseDate = bookDTO.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
	
	private static OutputBookDTO toDTO(BookEntity bookEntity) {
		Long bookId = bookEntity.getBookId();
		String titleBook = bookEntity.getTitleBook();
		String authorBook = bookEntity.getAuthorBook();
		String genreBook = bookEntity.getGenreBook();
		String releaseDate = bookEntity.getReleaseDate();
		Boolean isEmprestado = bookEntity.getIsOnloan();
		return new OutputBookDTO(bookId, titleBook, authorBook, genreBook, releaseDate, isEmprestado);
	}
	
	ReturnMessage<Long> insertBookIntoRepository(InputBookDTO book) {
		BookEntity bookEntity = BookController.toEntity(book);
		bookRepository.save(bookEntity);
	    return new ReturnMessage<>(bookEntity.getBookId(), ReturnMessage.LIVRO_CADASTRADO);
	}
	
	OutputBookDTO getBookFromRepository(Long bookCode) {
		Optional<BookEntity> bookEntity = getBookById(bookCode);
		if (bookEntity.isPresent()) {
			return BookController.toDTO(bookEntity.get());
		}
		return OutputBookDTO.NUll_VALUE;
	}

	Optional<BookEntity> getBookById(Long bookCode) {
		Optional<BookEntity> bookEntity = bookRepository.findById(bookCode);
		return bookEntity;
	}
	
	List<OutputBookDTO> getAllBooksFromRepository() {
		List<OutputBookDTO> selectedBooks = new ArrayList<>();
		Iterable<BookEntity> selectedEntities = bookRepository.findAll();
		for (BookEntity bookEntity : selectedEntities) {
			selectedBooks.add(BookController.toDTO(bookEntity));
		}
		return selectedBooks;
	}
	
	OutputBookDTO removeBookFromRepository(Long bookCode) {
		Optional<BookEntity> selectedBook = getBookById(bookCode);
		if (selectedBook.isPresent()) {
			OutputBookDTO oldBook = toDTO(selectedBook.get());
			bookRepository.delete(selectedBook.get());
			return oldBook;
		}
		return OutputBookDTO.NUll_VALUE;
	}
	
	OutputBookDTO updateBookInRepository(InputBookDTO newBook, Long codeBook) {
		Optional<BookEntity> selectedBook = getBookById(codeBook);
		if (selectedBook.isPresent()) {
			OutputBookDTO oldBook = toDTO(selectedBook.get());
			selectedBook.get().setTitleBook(newBook.getTitleBook());
			selectedBook.get().setAuthorBook(newBook.getAuthorBook());
			selectedBook.get().setGenreBook(newBook.getGenreBook());
			selectedBook.get().setReleaseDate(newBook.getReleaseDate());
			bookRepository.save(selectedBook.get());
			return oldBook;
		}
		return OutputBookDTO.NUll_VALUE;
	}
	
	ReturnMessage<OutputBookDTO> mensagemDeletado(OutputBookDTO deletedBook) {
		return new ReturnMessage<OutputBookDTO>(deletedBook, ReturnMessage.LIVRO_DELETADO);
	}
	
	ReturnMessage<OutputBookDTO> mensagemAtualizado(OutputBookDTO oldBook) {
		return new ReturnMessage<OutputBookDTO>(oldBook, ReturnMessage.LIVRO_ATUALIZADO);
	}
}