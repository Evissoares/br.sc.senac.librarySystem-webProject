package br.sc.senac.librarySystem;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class BorrowedHistoryController {

	private final BorrowedHistoryRepository borrowedHistoryRepository;
	private final ReaderRepository readerRepository;

	public BorrowedHistoryController(BorrowedHistoryRepository borrowedHistoryRepository, ReaderRepository readerRepository) {
		this.borrowedHistoryRepository = borrowedHistoryRepository;
		this.readerRepository = readerRepository;
	}

	public String generateHistoryAndSave(BookEntity book, Long userId) {
		Optional<ReaderEntity> selectedReader = readerRepository.findById(userId);
		if (selectedReader.isPresent()) {
			BorrowedHistoryEntity history = new BorrowedHistoryEntity(selectedReader.get(), book);
			LocalDateTime currentTime = LocalDateTime.now();
			history.setDateOfBorrow(currentTime);
			borrowedHistoryRepository.save(history);
			return "Empréstimo realizado com sucesso!";
		}
		return "Usuário não encontrado";
	}
}