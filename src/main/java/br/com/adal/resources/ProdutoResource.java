package br.com.adal.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.adal.entidade.Produto;
import br.com.adal.repository.ProdutoRepository;

@RestController
@RequestMapping("/leilao")
public class ProdutoResource {
	
	@Autowired
	private ProdutoRepository pr;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Produto> listaProdutos() {
		Iterable<Produto> listaProdutos = pr.findAll();
		return listaProdutos;
	}
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Produto> listaProdutos(String nome){
		Iterable<Produto> listaProdutos = (Iterable<Produto>) pr.findOne(nome);
		return listaProdutos;
	}
	
	@PostMapping()
	public Produto cadastraProduto(@RequestBody @Valid Produto produto) {
		return pr.save(produto);
	}
	
	@DeleteMapping()
	public Produto deletaProduto(@RequestBody Produto produto) {
		pr.delete(produto);
		return produto;
	}
	
	@DeleteMapping()
	public String deletaProduto(@RequestBody String nome) {
		pr.delete(nome);
		return nome;
	}
}
