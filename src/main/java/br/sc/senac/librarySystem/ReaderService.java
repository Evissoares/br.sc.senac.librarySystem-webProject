package br.sc.senac.librarySystem;

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
@RequestMapping("api/vbeta/librarysystem")
public class ReaderService {

	private final ReaderController readerController;
	
	public ReaderService(ReaderController readerController) {
		this.readerController = readerController; 
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
	ResponseEntity<ReaderDTO> deleteReader(@PathVariable Long readerId) {
		ReaderDTO selectedReader = this.readerController.removeReaderFromRepository(readerId);
		if(selectedReader.equals(ReaderDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
		return new ResponseEntity<ReaderDTO>(selectedReader, HttpStatus.OK);
	}
	
	@PutMapping("/updatereader/{readerId}")
	ResponseEntity<ReaderDTO> updateBook(@RequestBody ReaderDTO updateReader, @PathVariable Long readerId) {
		ReaderDTO updatedReader = this.readerController.updateReaderInRepository(updateReader, readerId);
		if(updatedReader.equals(ReaderDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
		return new ResponseEntity<ReaderDTO>(updatedReader, HttpStatus.OK);
	}
}
