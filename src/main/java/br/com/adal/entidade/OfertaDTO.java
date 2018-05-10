package br.com.adal.entidade;

public class OfertaDTO {
	
	private Long id;
	private double oferta;
	
	public OfertaDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getOferta() {
		return oferta;
	}
	
	public void setOferta(double oferta) {
		this.oferta = oferta;
	}
}
