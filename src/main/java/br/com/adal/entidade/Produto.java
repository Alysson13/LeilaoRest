package br.com.adal.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private double valorBase;
	
	@NotBlank
	private double incOmissao;
	
	@NotBlank
	private Date limiteVenda;
	
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
	
	public Date getLimiteVenda() {
		return limiteVenda;
	}
	
	public void setLimiteVenda(Date limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
	
	
}
