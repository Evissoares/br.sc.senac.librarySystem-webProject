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
	MensagensDeRetorno<Long> insertBook(@RequestBody EntradaBookDTO book) {
		return this.bookController.insertBookIntoRepository(book);
	}
	
	@GetMapping("/showbook/{bookCode}")
	ResponseEntity<SaidaBookDTO> showBook(@PathVariable Long bookCode) {
		SaidaBookDTO selectedBook = this.bookController.getBookFromRepository(bookCode);
		if(selectedBook.equals(SaidaBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SaidaBookDTO>(selectedBook, HttpStatus.OK);
	}
	
	@GetMapping("/showallbooks")
	List<SaidaBookDTO> showAllBooks() {
		return this.bookController.getAllBooksFromRepository();
	}
	
	@DeleteMapping("/deletebook/{codeBook}")
	ResponseEntity<MensagensDeRetorno<SaidaBookDTO>> deleteBook(@PathVariable Long codeBook) {
		SaidaBookDTO deletedBook = this.bookController.removeBookFromRepository(codeBook);
		if(deletedBook.equals(SaidaBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<SaidaBookDTO> mensagemDeRetorno = this.bookController.mensagemDeletado(deletedBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
	
	@PutMapping("/updatebook/{codeBook}")
	ResponseEntity<MensagensDeRetorno<SaidaBookDTO>> updateBook(@RequestBody EntradaBookDTO updateBook, @PathVariable Long codeBook) {
		SaidaBookDTO oldBook = this.bookController.updateBookInRepository(updateBook, codeBook);
		if(oldBook.equals(SaidaBookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<SaidaBookDTO> mensagemDeRetorno = this.bookController.mensagemAtualizado(oldBook);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
}