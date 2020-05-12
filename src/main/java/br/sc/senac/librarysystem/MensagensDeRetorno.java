package br.sc.senac.librarysystem;

public class MensagensDeRetorno {
	
	public static final MensagensDeRetorno LIVRO_ATUALIZADO = new MensagensDeRetorno("Livro atualizado");
	public static final MensagensDeRetorno LIVRO_DELETADO = new MensagensDeRetorno("Livro deletado");
	public static final MensagensDeRetorno LIVRO_CADASTRADO = new MensagensDeRetorno("Livro cadastrado");
	public static final MensagensDeRetorno LIVRO_NAO_ENCONTRADO = new MensagensDeRetorno("Livro não encontrado");
	
	public static final MensagensDeRetorno LEITOR_NAO_ENCONTRADO = new MensagensDeRetorno("Leitor não encontrado");
	public static final MensagensDeRetorno LEITOR_DELETADO = new MensagensDeRetorno("Leitor excluído");
	public static final MensagensDeRetorno LEITOR_ATUALIZADO = new MensagensDeRetorno("Leitor atualizado");
	public static final MensagensDeRetorno LEITOR_CADASTRADO = new MensagensDeRetorno("Leitor cadastrado");
	
	public static final MensagensDeRetorno HISTORICO_NAO_ENCONTRADO = new MensagensDeRetorno("Histórico não encontrado");
	public static final MensagensDeRetorno HISTORICO_CRIADO = new MensagensDeRetorno("Empréstimo realizado");
	
	private String message;

	public MensagensDeRetorno(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
