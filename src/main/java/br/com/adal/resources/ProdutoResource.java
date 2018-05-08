package br.com.adal.resources;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins="*")
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
	
	@PostMapping(value = "/oferta")
	public Produto aceitaOferta(@RequestBody @Valid Produto produto) {
		if(ps.obtemProdutoporNome(produto.getNome()) == null) {
			throw new EntityNotFoundException("O produto " + produto.getNome() + " não está cadastrado na base de dados.");
		}
		ps.checaData(produto.getNome());
		ps.deadEndOferta(produto.getNome());
		ps.aumentaPorOferta(produto.getNome(), produto.getOferta());
		return ps.obtemProdutoporNome(produto.getNome());
	}
	
	@PostMapping(value = "/incremento")
	public Produto aceitaIncremento(@RequestBody @Valid Produto produto) {
		if(ps.obtemProdutoporNome(produto.getNome()) == null) {
			throw new EntityNotFoundException("O produto " + produto.getNome() + " não está cadastrado na base de dados.");
		}
		ps.checaData(produto.getNome());
		ps.deadEndOferta(produto.getNome());
		ps.aumentaPorIncremento(produto.getNome());
		return ps.obtemProdutoporNome(produto.getNome());
	}
	
	@DeleteMapping(value = "/delete")
	public String deletaProduto(@RequestBody @Valid Produto produto) {
		if(ps.obtemProdutoporNome(produto.getNome()) == null) {
			throw new EntityNotFoundException("O produto " + produto.getNome() + " já não existe na base de dados.");
		}
		ps.deletaProdutos(produto.getNome());
		return "Produto " + produto.getNome() + " deletado do banco de dados.";
	}
		
}
