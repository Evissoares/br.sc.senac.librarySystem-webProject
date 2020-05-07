package br.sc.senac.librarySystem;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class BorrowedRecordsController {

	private final BorrowedRecordsRepository borrowedHistoryRepository;
	private final ReaderRepository readerRepository;

	public BorrowedRecordsController(BorrowedRecordsRepository borrowedHistoryRepository, ReaderRepository readerRepository) {
		this.borrowedHistoryRepository = borrowedHistoryRepository;
		this.readerRepository = readerRepository;
	}

	public String generateRecordAndSave(BookEntity book, Long userId) {
		Optional<ReaderEntity> selectedReader = readerRepository.findById(userId);
		if (selectedReader.isPresent()) {
			BorrowedRecordsEntity record = new BorrowedRecordsEntity(selectedReader.get(), book);
			LocalDateTime currentTime = LocalDateTime.now();
			record.setDateOfBorrowed(currentTime);
			borrowedHistoryRepository.save(record);
			return "Empréstimo realizado com sucesso! Id: " + record.getBorrowedId();
		}
		return "Usuário não encontrado";
	}
	
	public BorrowedRecordsDTO showRecord(Long borrowedId) {
		Optional<BorrowedRecordsEntity> selectedHistory = borrowedHistoryRepository.findById(borrowedId);
		if(selectedHistory.isPresent()) {
			return toDTO(selectedHistory.get());
		}
		return BorrowedRecordsDTO.NULL_VALUE;
	}
	
	private static BorrowedRecordsDTO toDTO(BorrowedRecordsEntity selectedRecord) {
		Long borrowedId = selectedRecord.getBorrowedId();
		ReaderEntity reader = selectedRecord.getReader();
		BookEntity book = selectedRecord.getBook();
		LocalDateTime dateOfBorrow = selectedRecord.getDateOfBorrowed();
		return new BorrowedRecordsDTO(borrowedId, reader, book, dateOfBorrow);
	}
}