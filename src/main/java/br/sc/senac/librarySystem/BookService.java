package br.sc.senac.librarySystem;

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
@RequestMapping("api/vbeta/libarysystem")
public class BookService {

	private final BookController bookController;
	
	public BookService(BookController bookController) {
		this.bookController = bookController;
	}
	
	@PostMapping("/insertbook")
	Long insertBook(@RequestBody BookDTO book) {
		return this.bookController.insertBookIntoRepository(book);
	}
	
	@GetMapping("/showbook/{codeBook}")
	ResponseEntity<BookDTO> showBook(@PathVariable Long codeBook) {
		BookDTO selectedBook = this.bookController.getBookFromRepository(codeBook);
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
	ResponseEntity<BookDTO> deleteBook(@PathVariable Long codeBook) {
		BookDTO selectedToRemove = this.bookController.removeBookFromRepository(codeBook);
		if(selectedToRemove.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
		return new ResponseEntity<BookDTO>(selectedToRemove, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{codeBook}")
	ResponseEntity<BookDTO> updateBook(@RequestBody BookDTO updateBook, @PathVariable Long codeBook) {
		BookDTO updatedBook = this.bookController.updateBookInRepository(updateBook, codeBook);
		if(updatedBook.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<BookDTO>(updatedBook, HttpStatus.OK);
	}
}