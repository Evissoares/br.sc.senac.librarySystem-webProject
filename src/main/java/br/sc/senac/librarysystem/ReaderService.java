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
public class ReaderService {

	private final ReaderController readerController;
	
	public ReaderService(ReaderController readerController) {
		this.readerController = readerController; 
	}
	
	@PostMapping("/salvarpadrao")
	void criaEntidadespadrao() {
		this.readerController.addDefault();
	}
	
	@PostMapping("/insertreader")
	Long insertReader(@RequestBody ReaderDTO reader) {
		return this.readerController.insertReaderIntoRepository(reader);
	}
	
	@GetMapping("/showreader/{readerId}")
	ResponseEntity<ReaderDTO> showReader(@PathVariable Long readerId) {
		ReaderDTO selectedReader = this.readerController.getReaderFromRepository(readerId);
		if(selectedReader.equals(ReaderDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ReaderDTO>(selectedReader, HttpStatus.OK);
	}
	
	@GetMapping("/showallreaders")
	List<ReaderDTO> showAllReaders() {
		return this.readerController.getAllReadersFromRepository();
	}
	
	@DeleteMapping("/deletereader/{readerId}")
	ResponseEntity<MensagensDeRetorno<ReaderDTO>> deleteReader(@PathVariable Long readerId) {
		ReaderDTO deletedReader = this.readerController.removeReaderFromRepository(readerId);
		if(deletedReader.equals(ReaderDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<ReaderDTO> mensagemDeRetorno = this.readerController.deleteReader(deletedReader);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
	
	@PutMapping("/updatereader/{readerId}")
	ResponseEntity<MensagensDeRetorno<ReaderDTO>> updateReader(@RequestBody ReaderDTO newReader, @PathVariable Long readerId) {
		ReaderDTO oldReader = this.readerController.updateReaderIntoRepository(newReader, readerId);
		if(oldReader.equals(ReaderDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		MensagensDeRetorno<ReaderDTO> mensagemDeRetorno = this.readerController.updateReader(oldReader);
		return new ResponseEntity<>(mensagemDeRetorno, HttpStatus.OK);
	}
}