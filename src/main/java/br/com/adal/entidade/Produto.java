package br.com.adal.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@NotBlank
	private String nome;
	
	@NotNull
	private double valorBase;
	
	@NotNull
	private double incOmissao;
	
	private Date limiteVenda;
	
	private double valorAtual;
	private double oferta;
	private String estado;
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public double getOferta() {
		return oferta;
	}

	public void setOferta(double oferta) {
		this.oferta = oferta;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getValorBase() {
		return valorBase;
	}
	
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	
	public double getIncOmissao() {
		return incOmissao;
	}
	
	public void setIncOmissao(double incOmissao) {
		this.incOmissao = incOmissao;
	}
	
	public Date getLimiteVenda() {
		return limiteVenda;
	}
	
	public void setLimiteVenda(Date limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
	
}
