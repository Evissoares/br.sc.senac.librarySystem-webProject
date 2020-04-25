package br.sc.senac.librarySystem;

import org.springframework.stereotype.Controller;

@Controller
public class LibraryController {

	private final ReaderRepository readerRepository;
	private final BookRepository bookRepository;
	
	LibraryController(ReaderRepository readerRepository, BookRepository bookRepository){
		this.readerRepository = readerRepository;
		this.bookRepository = bookRepository;
	}
}
