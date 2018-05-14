package br.com.adal.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adal.entidade.CadastroDTO;
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
	
	public Iterable<Produto> obtemProdutoporNome(String nome){
		return pr.findByNomeIgnoreCase(nome);
	}
	
	public Produto obtemProdutoporId(Long id) {
		return pr.findById(id);
	}
	
	public void salvaProdutos(CadastroDTO cadastroDTO)  {
		Produto pd1 = new Produto();
		pd1.setNome(cadastroDTO.getNome());
		pd1.setIncOmissao(cadastroDTO.getIncOmissao());
		pd1.setLimiteVenda(cadastroDTO.getLimiteVenda());
		pd1.setValorAtual(cadastroDTO.getValorAtual());
		byte[] b = Base64.getDecoder().decode(cadastroDTO.getFoto());
		pd1.setFoto(b);
		pr.save(pd1);
	}
	
	public void deletaProdutos(Long id) {
		pr.deleteById(id);
	}
	
	public void aumentaPorIncremento(Long id) {
		pd = pr.findById(id);
		pd.setValorAtual(pd.getValorAtual() + pd.getIncOmissao());
		pr.saveAndFlush(pd);
	}
	
	public void aumentaPorOferta(Long id, double oferta) {
		pd = pr.findById(id);
		pd.setOferta(oferta);
		if ((pd.getValorAtual() + pd.getIncOmissao())>=(pd.getOferta())) {
			pd.setValorAtual(pd.getValorAtual() + pd.getIncOmissao());
			pr.saveAndFlush(pd);
		}
		else {
			pd.setValorAtual(pd.getOferta());
			pr.saveAndFlush(pd);
		}
	}
	
	public void checaData(Long id) {
		pd = pr.findById(id);
		Instant ldt1 = Instant.parse(pd.getLimiteVenda());
		Instant ldt2 = Instant.now();
		Duration duration = Duration.between(ldt2, ldt1);
		if((duration.getSeconds()) < 10800) {
			pd.setEstado("FECHADO");
			pr.saveAndFlush(pd);
		}
		else { 
			pd.setEstado("ABERTO");
			pr.saveAndFlush(pd);
		}
	}
	
	public void checaData(CadastroDTO cadastroDTO) {
		Instant ldt1 = Instant.parse(cadastroDTO.getLimiteVenda());
		Instant ldt2 = Instant.now();
		Duration duration = Duration.between(ldt2, ldt1);
		if((duration.getSeconds()) < 10800) {
			cadastroDTO.setEstado("FECHADO");
		}
		else { 
			cadastroDTO.setEstado("ABERTO");
		}
	}
	
	public void deadEndOferta(Long id) {
		pd = pr.findById(id);
		Instant ldt1 = Instant.parse(pd.getLimiteVenda());
		Instant ldt2 = Instant.now();
		Duration duration = Duration.between(ldt2, ldt1);
		if((duration.getSeconds() < 10860) && (duration.getSeconds() > 10800)) {
			ldt1.plusSeconds(30);
			pd.setLimiteVenda(ldt1.toString());
			pr.saveAndFlush(pd);
		}
	}
}