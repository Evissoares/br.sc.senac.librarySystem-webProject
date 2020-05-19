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
	
	private static BookEntity toEntity(EntradaBookDTO bookDTO) {
		String titleBook = bookDTO.getTitleBook();
		String authorBook = bookDTO.getAuthorBook();
		String genreBook = bookDTO.getGenreBook();
		String releaseDate = bookDTO.getReleaseDate();
		return new BookEntity(titleBook, authorBook, genreBook, releaseDate);
	}
	
	private static SaidaBookDTO toDTO(BookEntity bookEntity) {
		Long bookId = bookEntity.getBookId();
		String titleBook = bookEntity.getTitleBook();
		String authorBook = bookEntity.getAuthorBook();
		String genreBook = bookEntity.getGenreBook();
		String releaseDate = bookEntity.getReleaseDate();
		Boolean isEmprestado = bookEntity.isEmprestado();
		return new SaidaBookDTO(bookId, titleBook, authorBook, genreBook, releaseDate, isEmprestado);
	}
	
	MensagensDeRetorno<Long> insertBookIntoRepository(EntradaBookDTO book) {
		BookEntity bookEntity = BookController.toEntity(book);
		bookRepository.save(bookEntity);
	    return new MensagensDeRetorno<>(bookEntity.getBookId(), MensagensDeRetorno.LIVRO_CADASTRADO);
	}
	
	SaidaBookDTO getBookFromRepository(Long bookCode) {
		Optional<BookEntity> bookEntity = getBookById(bookCode);
		if (bookEntity.isPresent()) {
			return BookController.toDTO(bookEntity.get());
		}
		return SaidaBookDTO.NUll_VALUE;
	}

	Optional<BookEntity> getBookById(Long bookCode) {
		Optional<BookEntity> bookEntity = bookRepository.findById(bookCode);
		return bookEntity;
	}
	
	List<SaidaBookDTO> getAllBooksFromRepository() {
		List<SaidaBookDTO> selectedBooks = new ArrayList<>();
		Iterable<BookEntity> selectedEntities = bookRepository.findAll();
		for (BookEntity bookEntity : selectedEntities) {
			selectedBooks.add(BookController.toDTO(bookEntity));
		}
		return selectedBooks;
	}
	
	SaidaBookDTO removeBookFromRepository(Long bookCode) {
		Optional<BookEntity> selectedBook = getBookById(bookCode);
		if (selectedBook.isPresent()) {
			SaidaBookDTO oldBook = toDTO(selectedBook.get());
			bookRepository.delete(selectedBook.get());
			return oldBook;
		}
		return SaidaBookDTO.NUll_VALUE;
	}
	
	SaidaBookDTO updateBookInRepository(EntradaBookDTO newBook, Long codeBook) {
		Optional<BookEntity> selectedBook = getBookById(codeBook);
		if (selectedBook.isPresent()) {
			SaidaBookDTO oldBook = toDTO(selectedBook.get());
			selectedBook.get().setTitleBook(newBook.getTitleBook());
			selectedBook.get().setAuthorBook(newBook.getAuthorBook());
			selectedBook.get().setGenreBook(newBook.getGenreBook());
			selectedBook.get().setReleaseDate(newBook.getReleaseDate());
			bookRepository.save(selectedBook.get());
			return oldBook;
		}
		return SaidaBookDTO.NUll_VALUE;
	}
	
	MensagensDeRetorno<SaidaBookDTO> mensagemDeletado(SaidaBookDTO deletedBook) {
		return new MensagensDeRetorno<SaidaBookDTO>(deletedBook, MensagensDeRetorno.LIVRO_DELETADO);
	}
	
	MensagensDeRetorno<SaidaBookDTO> mensagemAtualizado(SaidaBookDTO oldBook) {
		return new MensagensDeRetorno<SaidaBookDTO>(oldBook, MensagensDeRetorno.LIVRO_ATUALIZADO);
	}
}