package br.sc.senac.librarySystem;

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
}
