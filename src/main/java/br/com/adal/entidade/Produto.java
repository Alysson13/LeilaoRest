package br.com.adal.entidade;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private double valorBase;
	
	@NotBlank
	private double incOmissao;
	
	@NotBlank
	private Instant limiteVenda;
	
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
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
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
	
	public Instant getLimiteVenda() {
		return limiteVenda;
	}
	
	public void setLimiteVenda(Instant limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
	
}
