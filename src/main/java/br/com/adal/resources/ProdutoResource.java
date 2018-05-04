package br.com.adal.resources;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Iterables;

import br.com.adal.entidade.Produto;
import br.com.adal.service.ProdutoService;

@RestController
@RequestMapping("/leilao")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService ps;
	
	@GetMapping(produces = "application/json", value = "/")
	public @ResponseBody Iterable<Produto> listaProdutos() {
		if(Iterables.size(ps.obtemTodosProdutos()) == 0) {
			throw new EntityNotFoundException("Não existem produtos cadastrados na base de dados.");
		}
		return ps.obtemTodosProdutos();
	}
	
	@GetMapping(produces = "application/json", value = "/{nome}")
	public @ResponseBody Produto listaProdutos(@RequestBody @PathVariable("nome") String nome){
		if(ps.obtemProdutoporNome(nome) == null) {
			throw new EntityNotFoundException("O produto " + nome + " não está cadastrado na base de dados.");
		}
		return ps.obtemProdutoporNome(nome);
	}
	
	@PostMapping(value = "/cadastrounitario")
	public Produto cadastraProduto(@RequestBody @Valid Produto produto) {
		ps.salvaProdutos(produto);
		ps.checaData(produto.getNome());
		return produto;
	}
	
	@PostMapping(value = "/oferta/{nome}")
	public Produto aceitaOferta(@RequestBody @Valid @PathVariable("nome") String nome, double oferta) {
		if(ps.obtemProdutoporNome(nome) == null) {
			throw new EntityNotFoundException("O produto " + nome + " não está cadastrado na base de dados.");
		}
		ps.checaData(nome);
		ps.deadEndOferta(nome);
		ps.aumentaPorOferta(nome, oferta);
		return ps.obtemProdutoporNome(nome);
	}
	
	@PostMapping(value = "/incremento/{nome}")
	public Produto aceitaIncremento(@RequestBody @Valid @PathVariable("nome") String nome) {
		if(ps.obtemProdutoporNome(nome) == null) {
			throw new EntityNotFoundException("O produto " + nome + " não está cadastrado na base de dados.");
		}
		ps.checaData(nome);
		ps.deadEndOferta(nome);
		ps.aumentaPorIncremento(nome);
		return ps.obtemProdutoporNome(nome);
	}
	
	@DeleteMapping(value = "/delete/{nome}")
	public String deletaProduto(@RequestBody @PathVariable("nome") @Valid String nome) {
		if(ps.obtemProdutoporNome(nome) == null) {
			throw new EntityNotFoundException("O produto " + nome + " já não existe na base de dados.");
		}
		ps.deletaProdutos(nome);
		return "Produto " + nome + " deletado do banco de dados.";
	}
		
}
