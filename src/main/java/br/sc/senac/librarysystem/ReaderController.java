package br.sc.senac.librarysystem;

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

	public void addDefault() {
		ReaderEntity entidade = new ReaderEntity("Lucas", 18);
		readerRepository.save(entidade);
		
		entidade = new ReaderEntity("Everton", 27);
		readerRepository.save(entidade);
		
		entidade = new ReaderEntity("Marcelo", 33);
		readerRepository.save(entidade);
	}
	
	private static ReaderEntity toEntity(ReaderDTO reader) {
		String readerName = reader.getReaderName();
		Integer readerAge = reader.getReaderAge();
		return new ReaderEntity(readerName, readerAge);
	}
	
	private static ReaderDTO toDTO(ReaderEntity reader) {
		Long readerId = reader.getReaderId();
		String readerName = reader.getReaderName();
		Integer readerAge = reader.getReaderAge();
		return new ReaderDTO(readerId, readerName, readerAge);
	}
	
	Long insertReaderIntoRepository(ReaderDTO reader) {
		ReaderEntity readerEntity = ReaderController.toEntity(reader);
		readerRepository.save(readerEntity);
		return readerEntity.getReaderId();
	}
	
	ReaderDTO getReaderFromRepository(Long readerId) {
		Optional<ReaderEntity> selectedReaderEntity = getReaderById(readerId);
		if (selectedReaderEntity.isPresent()) {
			return ReaderController.toDTO(selectedReaderEntity.get());
		}
		return ReaderDTO.NULL_VALUE;
	}

	Optional<ReaderEntity> getReaderById(Long readerId) {
		Optional<ReaderEntity> selectedReaderEntity = readerRepository.findById(readerId);
		return selectedReaderEntity;
	}
	
	List<ReaderDTO> getAllReadersFromRepository() {
		List<ReaderDTO> selectedReaders = new ArrayList<>();
		Iterable<ReaderEntity> selectedEntities = readerRepository.findAll();
		for (ReaderEntity readerEntity : selectedEntities) {
			selectedReaders.add(ReaderController.toDTO(readerEntity));
		}
		return selectedReaders;
	}
	
    ReaderDTO removeReaderFromRepository(Long readerId) {
    	Optional<ReaderEntity> selectedReaderEntity = getReaderById(readerId);
    	if (selectedReaderEntity.isPresent()) {
    		ReaderDTO oldReader = toDTO(selectedReaderEntity.get());
    		readerRepository.delete(selectedReaderEntity.get());
    		return oldReader;
    	}
    	return ReaderDTO.NULL_VALUE;
    }
    
    ReaderDTO updateReaderIntoRepository(ReaderDTO newReader, Long readerId) {
    	Optional<ReaderEntity> selectedReader = getReaderById(readerId);
    	if (selectedReader.isPresent()) {
    		ReaderDTO oldReader = toDTO(selectedReader.get());
    		selectedReader.get().setReaderName(newReader.getReaderName());
    		selectedReader.get().setReaderAge(newReader.getReaderAge());
    		readerRepository.save(selectedReader.get());
    		return oldReader;
    	}
    	return ReaderDTO.NULL_VALUE;
    }
    
    MensagensDeRetorno<ReaderDTO> deleteReader(ReaderDTO deletedReader) {
    	return new MensagensDeRetorno<ReaderDTO>(deletedReader, MensagensDeRetorno.LEITOR_DELETADO);
    }
    
    MensagensDeRetorno<ReaderDTO> updateReader(ReaderDTO oldReader) {
    	return new MensagensDeRetorno<ReaderDTO>(oldReader, MensagensDeRetorno.LEITOR_ATUALIZADO);
    }
}