package br.sc.senac.librarySystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class ReaderController {
	
private final ReaderRepository readerRepository;
	
	ReaderController(ReaderRepository readerRepository) {
		this.readerRepository = readerRepository;
	}

	private static ReaderEntity toReaderEntity(ReaderDTO reader) {
		String readerName = reader.getReaderName();
		Integer readerAge = reader.getReaderAge();
		return new ReaderEntity(readerName, readerAge);
	}
	
	private static ReaderDTO toReaderDTO(ReaderEntity reader) {
		Long readerId = reader.getReaderId();
		String readerName = reader.getEntityReaderName();
		Integer readerAge = reader.getEntityReaderAge();
		return new ReaderDTO(readerId, readerName, readerAge);
	}
	
	Long insertReaderIntoRepository(ReaderDTO reader) {
		ReaderEntity readerEntity = ReaderController.toReaderEntity(reader);
		readerRepository.save(readerEntity);
		return readerEntity.getReaderId();
	}
	
	ReaderDTO getReaderFromRepository(Long readerId) {
		Optional<ReaderEntity> selectedReaderEntity = readerRepository.findById(readerId);
		if (selectedReaderEntity.isPresent()) {
			return ReaderController.toReaderDTO(selectedReaderEntity.get());
		}
		return ReaderDTO.NULL_VALUE;
	}
	
	List<ReaderDTO> getAllReadersFromRepository() {
		List<ReaderDTO> selectedReaders = new ArrayList<>();
		Iterable<ReaderEntity> selectedEntities = readerRepository.findAll();
		for (ReaderEntity readerEntity : selectedEntities) {
			selectedReaders.add(ReaderController.toReaderDTO(readerEntity));
		}
		return selectedReaders;
	}
	
    ReaderDTO removeReaderFromRepository(Long readerId) {
    	Optional<ReaderEntity> selectedReaderEntity = readerRepository.findById(readerId);
    	if (selectedReaderEntity.isPresent()) {
    		ReaderDTO removedReader = ReaderController.toReaderDTO(selectedReaderEntity.get());
    		readerRepository.delete(selectedReaderEntity.get());
    		return removedReader;
    	}
    	return ReaderDTO.NULL_VALUE;
    }
	
    private static void updateReaderEntityFromDTO(ReaderEntity oldReader, ReaderDTO newReader) {
    	oldReader.setEntityReaderName(newReader.getReaderName());
    	oldReader.setEntityReaderAge(newReader.getReaderAge());
    }
    
    ReaderDTO updateReaderInRepository(ReaderDTO updateReader, Long readerId) {
    	Optional<ReaderEntity> selectedReader = readerRepository.findById(readerId);
    	if (selectedReader.isPresent()) {
    		ReaderEntity readerForUpdate = selectedReader.get();
    		ReaderDTO oldReader = ReaderController.toReaderDTO(selectedReader.get());
    		ReaderController.updateReaderEntityFromDTO(readerForUpdate, updateReader);
    		readerRepository.save(readerForUpdate);
    		return oldReader;
    	}
    	return ReaderDTO.NULL_VALUE;
    }
}