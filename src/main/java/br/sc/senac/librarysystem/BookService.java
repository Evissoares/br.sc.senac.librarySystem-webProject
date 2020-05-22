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
	
	@PostMapping("/adddefaultbook")
	void addDefaultBook() {
		this.bookController.addDefault();
	}
	
	@PostMapping("/insertbook")
	ReturnMessage<Long> insertBook(@RequestBody InputBookDTO book) {
		return this.bookController.insertBookIntoRepository(book);
	}
	
	@GetMapping("/showbook/{bookCode}")
	ResponseEntity<OutputBookDTO> showBook(@PathVariable Long bookCode) {
		OutputBookDTO selectedBook = this.bookController.getBookFromRepository(bookCode);
		if(selectedBook.equals(OutputBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OutputBookDTO>(selectedBook, HttpStatus.OK);
	}
	
	@GetMapping("/showallbooks")
	List<OutputBookDTO> showAllBooks() {
		return this.bookController.getAllBooksFromRepository();
	}
	
	@DeleteMapping("/deletebook/{codeBook}")
	ResponseEntity<ReturnMessage<OutputBookDTO>> deleteBook(@PathVariable Long codeBook) {
		OutputBookDTO deletedBook = this.bookController.removeBookFromRepository(codeBook);
		if(deletedBook.equals(OutputBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ReturnMessage<OutputBookDTO> mensagemDeRetorno = this.bookController.mensagemDeletado(deletedBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{codeBook}")
	ResponseEntity<ReturnMessage<OutputBookDTO>> updateBook(@RequestBody InputBookDTO updateBook, @PathVariable Long codeBook) {
		OutputBookDTO oldBook = this.bookController.updateBookInRepository(updateBook, codeBook);
		if(oldBook.equals(OutputBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ReturnMessage<OutputBookDTO> mensagemDeRetorno = this.bookController.mensagemAtualizado(oldBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
}