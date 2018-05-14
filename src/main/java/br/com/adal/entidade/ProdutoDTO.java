package br.com.adal.entidade;

public class ProdutoDTO {
	
	private String nome;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ProdutoDTO() {
		
	}
}
