package br.sc.senac.librarysystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
public class RequestLoanController {

	private RequestLoanRepository requestLoanRepository;
	private final ReaderController readerController;
	private final BookController bookController;

	public RequestLoanController(RequestLoanRepository requestLoanRepository,
			ReaderController readerController, BookController bookController) {
		this.requestLoanRepository = requestLoanRepository;
		this.readerController = readerController;
		this.bookController = bookController;
	}

	private ReturnRequestLoanDTO toDTO(RequestLoanEntity entidade) {
		Long emprestimoId = entidade.getLoanId();

		Long bookId = entidade.getBookId();
		String titleBook = entidade.getTitleBook();
		String authorBook = entidade.getAuthorBook();

		Long readerId = entidade.getReaderId();
		String readerName = entidade.getReaderName();
		return new ReturnRequestLoanDTO(emprestimoId, readerId, readerName, bookId, titleBook, authorBook);
	}

	public ReturnMessage<Long> solicitarESalvar(Long readerId, Long bookCode) {
		Optional<ReaderEntity> selectedReader = this.readerController.getReaderById(readerId);
		Optional<BookEntity> selectedBook = this.bookController.getBookById(bookCode);
		if (selectedReader.isPresent()) {
			if (selectedBook.isPresent()) {
				if (!selectedBook.get().getIsOnloan()) {
					selectedBook.get().setStatus(true);
					RequestLoanEntity novoRegistro = new RequestLoanEntity(
							selectedReader.get(), selectedBook.get());
					requestLoanRepository.save(novoRegistro);
					return new ReturnMessage<Long>(novoRegistro.getLoanId(),
							ReturnMessage.HISTORICO_CRIADO);
				}
				return new ReturnMessage<Long>(bookCode, ReturnMessage.LIVRO_INDISPONIVEL);
			}
			return new ReturnMessage<Long>(bookCode, ReturnMessage.LIVRO_NAO_ENCONTRADO);
		}
		return new ReturnMessage<Long>(readerId, ReturnMessage.LEITOR_NAO_ENCONTRADO);
	}

	public ReturnRequestLoanDTO pegarHistorico(Long emprestimoId) {
		Optional<RequestLoanEntity> historicoSelecionado = requestLoanRepository
				.findById(emprestimoId);
		if (historicoSelecionado.isPresent()) {
			return toDTO(historicoSelecionado.get());
		}
		return ReturnRequestLoanDTO.NULL_VALUE;
	}

	List<ReturnRequestLoanDTO> pegarTodosOsHistoricos() {
		List<ReturnRequestLoanDTO> historicosSelecionados = new ArrayList<>();
		Iterable<RequestLoanEntity> entidades = requestLoanRepository.findAll();
		for (RequestLoanEntity entidade : entidades) {
			historicosSelecionados.add(toDTO(entidade));
		}
		return historicosSelecionados;
	}

	public ReturnMessage<Long> desfazerEmprestimo(Long bookCode) {
		Optional<BookEntity> livroParaDevolver = this.bookController.getBookById(bookCode);
		if (livroParaDevolver.isPresent()) {
			if (livroParaDevolver.get().getIsOnloan()) {
				livroParaDevolver.get().setStatus(false);
				this.bookController.salvarEntidadeLivro((livroParaDevolver.get()));
				return new ReturnMessage<Long>(bookCode, ReturnMessage.LIVRO_DEVOLVIDO);
			}
			return new ReturnMessage<Long>(bookCode, ReturnMessage.LIVRO_DISPONIVEL);
		}
		return new ReturnMessage<Long>(bookCode, ReturnMessage.LIVRO_NAO_ENCONTRADO);
	}
}