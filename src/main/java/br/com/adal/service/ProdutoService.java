package br.com.adal.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adal.entidade.Produto;
import br.com.adal.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository pr;
	
	Produto pd = new Produto();
	
	public Iterable<Produto> obtemTodosProdutos() {
		return pr.findAll();
	}
	
	public Produto obtemProdutoporNome(String nome){
		return pr.findOne(nome);
	}
	
	public void salvaProdutos(Produto produto) {
		pr.save(produto);
	}
	
	public void deletaProdutos(String nome) {
		pr.delete(nome);
	}
	
	public void aumentaPorIncremento(String nome) {
		pd = pr.findOne(nome);
		pd.setValorAtual(pd.getValorAtual() + pd.getIncOmissao());
		pr.saveAndFlush(pd);
	}
	
	public void aumentaPorOferta(String nome) {
		pd = pr.findOne(nome);
		if ((pd.getValorAtual() + pd.getIncOmissao())<pd.getOferta()) {
			pd.setValorAtual(pd.getValorAtual() + pd.getIncOmissao());
			pr.saveAndFlush(pd);
		}
		else {
			pd.setValorAtual(pd.getOferta());
			pr.saveAndFlush(pd);
		}
	}
	
	public void checaData(String nome) {
		pd = pr.findOne(nome);
		Instant instant1 = pd.getLimiteVenda();
		Instant instant2 = Instant.now();
		if(instant1.compareTo(instant2)<0) {
			pd.setEstado("FECHADO");
			pr.saveAndFlush(pd);
		}
		else { 
			pd.setEstado("ABERTO");
			pr.saveAndFlush(pd);
		}
	}
	
	public void deadEndOferta(String nome) {
		pd = pr.findOne(nome);
		Instant instant1 = pd.getLimiteVenda();
		Instant instant2 = Instant.now();
		LocalDateTime ldt1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
		LocalDateTime ldt2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
		int hour1 = ldt1.getHour();
		int hour2 = ldt2.getHour();
		int minute1 = ldt1.getMinute();
		int minute2 = ldt2.getMinute();
		if((hour1 - hour2 == 0) && (minute2 - minute1 <= 1) && (minute2 - minute1 > 0)) {
			instant1 = ldt1.atZone(ZoneId.systemDefault()).toInstant();
			pd.setLimiteVenda(instant1.plusSeconds(30));
			pr.saveAndFlush(pd);
		}
	}
}