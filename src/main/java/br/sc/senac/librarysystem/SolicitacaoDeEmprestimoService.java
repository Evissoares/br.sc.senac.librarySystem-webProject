package br.sc.senac.librarysystem;

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
	
	/*@GetMapping("/pegartodososhistoricos")
	List<RetornoSolicitacaoDeEmprestimoDTO> pegarTodosOsHistoricos() {
		return this.solicitacaoController.pegarTodosOsHistoricos();
	}
	
	@GetMapping("/details/{emprestimoId}")
	RetornoSolicitacaoDeEmprestimoDTO pegarhistorico(@PathVariable Long emprestimoId) {
		return this.solicitacaoController.pegarHistorico(emprestimoId);
	}*/
}