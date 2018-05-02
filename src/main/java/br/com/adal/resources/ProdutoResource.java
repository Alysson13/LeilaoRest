package br.com.adal.resources;

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

import br.com.adal.entidade.Produto;
import br.com.adal.service.ProdutoService;

@RestController
@RequestMapping("/leilao")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService ps;
	
	@GetMapping(produces = "application/json", value = "/")
	public @ResponseBody void listaProdutos() {
		ps.obtemTodosProdutos();
	}
	
	@GetMapping(produces = "application/json", value = "/{nome}")
	public @ResponseBody void listaProdutos(@PathVariable("nome") String nome){
		ps.obtemProdutoporNome(nome);
	}
	
	@PostMapping(value = "/cadastrounitario/{nome}")
	public Produto cadastraProduto(@RequestBody @Valid @PathVariable("nome") Produto produto) {
		ps.salvaProdutos(produto);
		ps.checaData(produto.getNome());
		return produto;
	}
	
	@PostMapping(value = "/oferta/{nome}")
	public void aceitaOferta(@RequestBody @Valid @PathVariable("nome") String nome) {
		ps.checaData(nome);
		ps.deadEndOferta(nome);
		ps.aumentaPorOferta(nome);
	}
	
	@PostMapping(value = "/incremento/{nome}")
	public void aceitaIncremento(@RequestBody @Valid @PathVariable("nome") String nome) {
		ps.checaData(nome);
		ps.deadEndOferta(nome);
		ps.aumentaPorIncremento(nome);
	}
	
	@DeleteMapping(value = "/delete/{nome}")
	public void deletaProduto(@RequestBody @PathVariable("nome") @Valid String nome) {
		ps.deletaProdutos(nome);
	}
		
}
