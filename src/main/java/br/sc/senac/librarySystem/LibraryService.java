package br.sc.senac.librarySystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vBeta/librarySystem")
public class LibraryService {
	
	private final LibraryController libraryController;
	
	LibraryService(LibraryController libraryController) {
		this.libraryController = libraryController;
	}

}