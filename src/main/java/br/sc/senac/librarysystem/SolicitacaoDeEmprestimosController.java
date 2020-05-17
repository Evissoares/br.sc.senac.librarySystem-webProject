package br.sc.senac.librarysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class SolicitacaoDeEmprestimosController {

	private SolicitacaoDeEmprestimosRepository solicitacaoDeEmprestimosRepository;
	private final ReaderController readerController;
	private final BookController bookController;

	public SolicitacaoDeEmprestimosController(SolicitacaoDeEmprestimosRepository solicitacaoDeEmprestimosRepository, ReaderController readerController, BookController bookController) {
		this.solicitacaoDeEmprestimosRepository = solicitacaoDeEmprestimosRepository;
		this.readerController = readerController;
		this.bookController = bookController;
	}

	private RetornoSolicitacaoDeEmprestimoDTO toDTO(SolicitacaoDeEmprestimosEntity entidade) {
		Long emprestimoId = entidade.getEmprestimoId();
		
		Long bookId = entidade.getBookId();
		String titleBook = entidade.getTitleBook();
		String authorBook = entidade.getAuthorBook();
		
		Long readerId = entidade.getReaderId();
		String readerName = entidade.getReaderName();
		return new RetornoSolicitacaoDeEmprestimoDTO(emprestimoId, readerId, readerName, bookId, titleBook, authorBook);
	}
	
	public MensagensDeRetorno<Long> solicitarESalvar(Long readerId, Long bookCode) {
		Optional<ReaderEntity> selectedReader = this.readerController.getReaderById(readerId);
		Optional<BookEntity> selectedBook = this.bookController.getBookById(bookCode);	
		if(selectedReader.isPresent()) {
			if(selectedBook.isPresent()) {
				SolicitacaoDeEmprestimosEntity novoRegistro = new SolicitacaoDeEmprestimosEntity(selectedReader.get(), selectedBook.get());
				solicitacaoDeEmprestimosRepository.save(novoRegistro);
				return new MensagensDeRetorno<Long>(novoRegistro.getEmprestimoId(), MensagensDeRetorno.HISTORICO_CRIADO);
			}
			return new MensagensDeRetorno<Long>(bookCode, MensagensDeRetorno.LIVRO_NAO_ENCONTRADO);
		}
		return new MensagensDeRetorno<Long>(readerId, MensagensDeRetorno.LEITOR_NAO_ENCONTRADO);
	}
	
	public RetornoSolicitacaoDeEmprestimoDTO pegarHistorico(Long emprestimoId) {
		Optional<SolicitacaoDeEmprestimosEntity> historicoSelecionado = solicitacaoDeEmprestimosRepository.findById(emprestimoId);
		if(historicoSelecionado.isPresent()) {
			return toDTO(historicoSelecionado.get());
		}
		return RetornoSolicitacaoDeEmprestimoDTO.NULL_VALUE;
	}
	/*
	List<RetornoSolicitacaoDeEmprestimoDTO> pegarTodosOsHistoricos() {
		List<RetornoSolicitacaoDeEmprestimoDTO> historicosSelecionados = new ArrayList<>();
		Iterable<SolicitacaoDeEmprestimosEntity> entidades = solicitacaoDeEmprestimosRepository.findAll();
		for(SolicitacaoDeEmprestimosEntity entidade : entidades) {
			historicosSelecionados.add(toDTO(entidade));
		}
		return historicosSelecionados;
	}*/
}