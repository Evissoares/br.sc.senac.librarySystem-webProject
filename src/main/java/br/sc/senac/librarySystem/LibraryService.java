package br.sc.senac.librarySystem;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vBeta/librarySystem")
public class LibraryService {
	
	private final LibraryController libraryController;
	
	LibraryService(LibraryController libraryController) {
		this.libraryController = libraryController;
	}
	
	@PostMapping("/insertBook")
	Long insertBook(@RequestBody BookDTO book) {
		return this.libraryController.insertBookIntoRepository(book);
	}
	
	@GetMapping("/showBook/{codeBook}")
	ResponseEntity<BookDTO> showBook(@PathVariable Long codeBook) {
		BookDTO selectedBook = this.libraryController.getBookFromRepository(codeBook);
		if(selectedBook.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<BookDTO>(selectedBook, HttpStatus.OK);
	}
	
	@GetMapping("/showAllBooks")
	List<BookDTO> showAllBooks() {
		return this.libraryController.getAllBooksFromRepository();
	}
	
	@DeleteMapping("/deleteBook/{codeBook}")
	ResponseEntity<BookDTO> deleteBook(@PathVariable Long codeBook) {
		BookDTO selectedToRemove = this.libraryController.removeBookFromRepository(codeBook);
		if(selectedToRemove.equals(BookDTO.NUll_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<BookDTO>(HttpStatus.OK);
	}
}
