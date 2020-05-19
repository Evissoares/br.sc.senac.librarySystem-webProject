package br.sc.senac.librarysystem;

public class MensagensDeRetorno<DTO> {

	public static final String LIVRO_ATUALIZADO = "Livro atualizado";
	public static final String LIVRO_DELETADO = "Livro deletado";
	public static final String LIVRO_CADASTRADO = "Livro cadastrado";
	public static final String LIVRO_NAO_ENCONTRADO = "Livro não encontrado";
	public static final String LIVRO_INDISPONIVEL = "Livro já emprestado";
	public static final String LIVRO_DISPONIVEL = "Livro já devolvido";
	public static final String LIVRO_DEVOLVIDO = "Devolução realizada com sucesso";
	
	public static final String LEITOR_NAO_ENCONTRADO = "Leitor não encontrado";
	public static final String LEITOR_DELETADO = "Leitor excluído";
	public static final String LEITOR_ATUALIZADO = "Leitor atualizado";
	public static final String LEITOR_CADASTRADO = "Leitor cadastrado";
	
	public static final String HISTORICO_NAO_ENCONTRADO = "Histórico não encontrado";
	public static final String HISTORICO_CRIADO = "Empréstimo realizado";
	
	private String message;
	private DTO response;
	
	public MensagensDeRetorno(DTO dto, String message) {
		this.message = message;
		this.response = dto;
	}
	
	public MensagensDeRetorno(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

	public DTO getResponse() {
		return response;
	}
}
