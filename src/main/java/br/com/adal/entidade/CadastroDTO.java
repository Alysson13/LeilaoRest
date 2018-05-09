package br.com.adal.entidade;

import java.time.Instant;

public class CadastroDTO {
	
	private String nome;
	private double valorAtual;
	private double incOmissao;
	private Instant limiteVenda;
	
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

	public Instant getLimiteVenda() {
		return limiteVenda;
	}

	public void setLimiteVenda(Instant limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
}
