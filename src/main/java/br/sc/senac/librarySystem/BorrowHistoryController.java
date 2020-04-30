package br.sc.senac.librarySystem;

import org.springframework.stereotype.Controller;

@Controller
public class BorrowHistoryController {

	private final ReaderRepository readerRepository;
	private final BookRepository bookRepository;

	public BorrowHistoryController(ReaderRepository readerRepository, BookRepository bookRepository) {
		this.readerRepository = readerRepository;
		this.bookRepository = bookRepository;
	}
}
