package br.sc.senac.librarysystem;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/librarysystem")
public class BookService {

	private final BookController bookController;
	
	public BookService(BookController bookController) {
		this.bookController = bookController;
	}
	
	@PostMapping("/adddefault")
	void entidadesPadrao() {
		this.bookController.addDefault();
	}
	
	@PostMapping("/insertbook")
	Long insertBook(@RequestBody BookDTO book) {
		return this.bookController.insertBookIntoRepository(book);
	}
	
	@GetMapping("/showbook/{bookCode}")
	ResponseEntity<BookDTO> showBook(@PathVariable Long bookCode) {
		BookDTO selectedBook = this.bookController.getBookFromRepository(bookCode);
		if(selectedBook.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookDTO>(selectedBook, HttpStatus.OK);
	}
	
	@GetMapping("/showallbooks")
	List<BookDTO> showAllBooks() {
		return this.bookController.getAllBooksFromRepository();
	}
	
	@DeleteMapping("/deletebook/{codeBook}")
	ResponseEntity<MensagensDeRetorno<BookDTO>> deleteBook(@PathVariable Long codeBook) {
		BookDTO deletedBook = this.bookController.removeBookFromRepository(codeBook);
		if(deletedBook.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<BookDTO> mensagemDeRetorno = this.bookController.mensagemDeletado(deletedBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{codeBook}")
	ResponseEntity<MensagensDeRetorno<BookDTO>> updateBook(@RequestBody BookDTO updateBook, @PathVariable Long codeBook) {
		BookDTO oldBook = this.bookController.updateBookInRepository(updateBook, codeBook);
		if(oldBook.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<BookDTO> mensagemDeRetorno = this.bookController.mensagemAtualizado(oldBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
}