package br.com.adal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adal.entidade.Produto;
import br.com.adal.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository pr;
	
	@Autowired
	private Produto pd;
	
	public Iterable<Produto> obtemTodosProdutos(){
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
		pr.save(pd);
	}
	
	public void aumentaPorOferta(String nome) {
		pd = pr.findOne(nome);
		if ((pd.getValorAtual() + pd.getIncOmissao())<pd.getOferta()) {
			pd.setValorAtual(pd.getValorAtual() + pd.getIncOmissao());
			pr.save(pd);
		}
		else {
			pd.setValorAtual(pd.getOferta());
			pr.save(pd);
		}
	}
}
