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
public class SolicitacaoDeEmprestimoService {

	private final SolicitacaoDeEmprestimosController solicitacaoController;
	
	public SolicitacaoDeEmprestimoService(SolicitacaoDeEmprestimosController solicitacaoController) {
		this.solicitacaoController = solicitacaoController;
	}
	
	@PostMapping("/requisicao")
    MensagensDeRetorno<Long> solicitar(@RequestBody SolicitacaoDeEmprestimosDTO solicitacao) {
		return this.solicitacaoController.solicitarESalvar(solicitacao.getReaderId(), solicitacao.getBookId());
	}
	
	@GetMapping("/pegartodososhistoricos")
	List<RetornoSolicitacaoDeEmprestimoDTO> pegarTodosOsHistoricos() {
		return this.solicitacaoController.pegarTodosOsHistoricos();
	}
	
	@GetMapping("/details/{emprestimoId}")
	ResponseEntity<RetornoSolicitacaoDeEmprestimoDTO> pegarhistorico(@PathVariable Long emprestimoId) {
		RetornoSolicitacaoDeEmprestimoDTO retornoDeHistorico = solicitacaoController.pegarHistorico(emprestimoId);
		if(retornoDeHistorico.equals(RetornoSolicitacaoDeEmprestimoDTO.NULL_VALUE)) {
 			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RetornoSolicitacaoDeEmprestimoDTO>(retornoDeHistorico, HttpStatus.OK);
	}
	
	@PatchMapping("/desfazerrequisicao/{bookCode}")
	MensagensDeRetorno<Long> desfazerEmprestimo(@PathVariable Long bookCode) {
		return this.solicitacaoController.desfazerEmprestimo(bookCode);
	}
}