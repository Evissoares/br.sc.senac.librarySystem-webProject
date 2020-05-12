package br.sc.senac.librarysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class SolicitacaoDeEmprestimosController {

	private BookRepository bookRepository;
	private ReaderRepository readerRepository;
	private SolicitacaoDeEmprestimosRepository solicitacaoDeEmprestimosRepository;

	public SolicitacaoDeEmprestimosController(BookRepository bookRepository, ReaderRepository readerRepository, SolicitacaoDeEmprestimosRepository solicitacaoDeEmprestimosRepository) {
		this.bookRepository = bookRepository;
		this.readerRepository = readerRepository;
		this.solicitacaoDeEmprestimosRepository = solicitacaoDeEmprestimosRepository;
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
	
	public Long solicitarESalvar(SolicitacaoDeEmprestimosDTO solicitacao) {
		Optional<BookEntity> currentBook = bookRepository.findById(solicitacao.getBookId());
		Optional<ReaderEntity> currentReader = readerRepository.findById(solicitacao.getReaderId());
		
		SolicitacaoDeEmprestimosEntity solicitacaoRegistro = new SolicitacaoDeEmprestimosEntity(currentReader.get(), currentBook.get());
		solicitacaoDeEmprestimosRepository.save(solicitacaoRegistro);
		return solicitacaoRegistro.getEmprestimoId();
	}
	
	public RetornoSolicitacaoDeEmprestimoDTO pegarHistorico(Long emprestimoId) {
		Optional<SolicitacaoDeEmprestimosEntity> historicoSelecionado = solicitacaoDeEmprestimosRepository.findById(emprestimoId);
		SolicitacaoDeEmprestimosEntity entidadeSelecionada = historicoSelecionado.get();
		return toDTO(entidadeSelecionada);
	}
	
	List<RetornoSolicitacaoDeEmprestimoDTO> pegarTodosOsHistoricos() {
		List<RetornoSolicitacaoDeEmprestimoDTO> historicosSelecionados = new ArrayList<>();
		Iterable<SolicitacaoDeEmprestimosEntity> entidades = solicitacaoDeEmprestimosRepository.findAll();
		for(SolicitacaoDeEmprestimosEntity entidade : entidades) {
			historicosSelecionados.add(toDTO(entidade));
		}
		return historicosSelecionados;
	}
}