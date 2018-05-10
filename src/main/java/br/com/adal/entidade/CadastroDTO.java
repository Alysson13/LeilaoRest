package br.com.adal.entidade;

public class CadastroDTO {
	
	private String nome;
	private double valorAtual;
	private double incOmissao;
	private String limiteVenda;
	
	public CadastroDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public double getIncOmissao() {
		return incOmissao;
	}

	public void setIncOmissao(double incOmissao) {
		this.incOmissao = incOmissao;
	}

	public String getLimiteVenda() {
		return limiteVenda;
	}

	public void setLimiteVenda(String limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
}
