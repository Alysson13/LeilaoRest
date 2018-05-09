package br.com.adal.entidade;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private double id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	private double valorAtual;
	
	@NotNull
	private double incOmissao;
	
	private Instant limiteVenda;
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
