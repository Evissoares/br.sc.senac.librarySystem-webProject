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
	ResponseEntity<MensagensDeRetorno> deleteBook(@PathVariable Long codeBook) {
		MensagensDeRetorno message = this.bookController.removeBookFromRepository(codeBook);
		if(message.equals(MensagensDeRetorno.LIVRO_DELETADO)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{codeBook}")
	ResponseEntity<MensagensDeRetorno> updateBook(@RequestBody BookDTO updateBook, @PathVariable Long codeBook) {
		MensagensDeRetorno message = this.bookController.updateBookInRepository(updateBook, codeBook);
		if(message.equals(MensagensDeRetorno.LIVRO_ATUALIZADO)) {
			return new ResponseEntity<>(message, HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}