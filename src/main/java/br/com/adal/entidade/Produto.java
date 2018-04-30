package br.com.adal.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;
	
	private String nome;
	private double valorBase;
	private double incOmissao;
	private String limiteVenda;
	private double valorAtual;
	private double oferta;
	private String estado;
	
	public Produto() {
		
	}
	
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

	@NotBlank(message = "Nome do produto não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Nome do produto deve conter entre 5 e 200 caracteres.")
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank(message = "Valor base da licitação não pode ser vazio.")
	public double getValorBase() {
		return valorBase;
	}
	
	public void setValorBase(double valorBase) {
		this.valorBase = valorBase;
	}
	
	@NotBlank(message = "Valor de incremento por omissão não pode ser vazio.")
	public double getIncOmissao() {
		return incOmissao;
	}
	
	public void setIncOmissao(double incOmissao) {
		this.incOmissao = incOmissao;
	}
	
	@NotBlank(message = "Data limite de licitação não pode ser vazia.")
	public String getLimiteVenda() {
		return limiteVenda;
	}
	
	public void setLimiteVenda(String limiteVenda) {
		this.limiteVenda = limiteVenda;
	}
	
	@Override
	public String toString() {
		return "Produto para leilão [Codigo=" + codigo +", Nome=" + nome +", Valor base=" + valorBase + ", Valor de incremento por omissão=" + incOmissao + ", Data limite de venda=" + limiteVenda + "]";
	}
}
