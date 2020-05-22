package br.sc.senac.librarysystem;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/librarysystem")
public class RequestLoanService {

	private final RequestLoanController solicitacaoController;
	
	public RequestLoanService(RequestLoanController solicitacaoController) {
		this.solicitacaoController = solicitacaoController;
	}
	
	@PostMapping("/realizar-emprestimo")
    ReturnMessage<Long> solicitar(@RequestBody RequestLoanDTO solicitacao) {
		return this.solicitacaoController.solicitarESalvar(solicitacao.getReaderId(), solicitacao.getBookId());
	}
	
	@GetMapping("/pegartodososhistoricos")
	List<ReturnRequestLoanDTO> pegarTodosOsHistoricos() {
		return this.solicitacaoController.pegarTodosOsHistoricos();
	}
	
	@GetMapping("/detalhes/{emprestimoId}")
	ResponseEntity<ReturnRequestLoanDTO> pegarhistorico(@PathVariable Long emprestimoId) {
		ReturnRequestLoanDTO retornoDeHistorico = solicitacaoController.pegarHistorico(emprestimoId);
		if(retornoDeHistorico.equals(ReturnRequestLoanDTO.NULL_VALUE)) {
 			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ReturnRequestLoanDTO>(retornoDeHistorico, HttpStatus.OK);
	}
	
	@PatchMapping("/desfazeremprestimo/{bookCode}")
	ReturnMessage<Long> desfazerEmprestimo(@PathVariable Long bookCode) {
		return this.solicitacaoController.desfazerEmprestimo(bookCode);
	}
}