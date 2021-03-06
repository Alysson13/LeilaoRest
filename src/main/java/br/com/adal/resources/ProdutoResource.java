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

import br.com.adal.entidade.CadastroDTO;
import br.com.adal.entidade.IncrementoDTO;
import br.com.adal.entidade.OfertaDTO;
import br.com.adal.entidade.Produto;
import br.com.adal.entidade.ProdutoDTO;
import br.com.adal.service.ProdutoService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService ps;
	
	@GetMapping(produces = "application/json", value = "/produtos")
	public @ResponseBody Iterable<Produto> listaProdutos() {
		if(Iterables.size(ps.obtemTodosProdutos()) == 0) {
			throw new EntityNotFoundException("Não existem produtos cadastrados na base de dados.");
		}
		return ps.obtemTodosProdutos();
	}
	
	@GetMapping(produces = "application/json", value = "/produtos/{nome}")
	public @ResponseBody Iterable<Produto> listaProdutos(@RequestBody @PathVariable("nome") String nome){
		if(ps.obtemProdutoporNome(nome) == null) {
			throw new EntityNotFoundException("O produto " + nome + " não está cadastrado na base de dados.");
		}
		return ps.obtemProdutoporNome(nome);
	}
	
	@GetMapping(produces = "application/json", value = "/produtos/id/{id}")
	public @ResponseBody Produto listaProdutos(@RequestBody @PathVariable("id") Long id){
		if(ps.obtemProdutoporId(id) == null) {
			throw new EntityNotFoundException("O produto não está cadastrado na base de dados.");
		}
		return ps.obtemProdutoporId(id);
	}
	
	@PostMapping(value = "/produtos")
	public String cadastraProduto(@RequestBody @Valid CadastroDTO cadastroDTO)  {
		ps.checaData(cadastroDTO);
		ps.salvaProdutos(cadastroDTO);
		return "Produto cadastrado com sucesso!";
	}
	
	@PostMapping(value = "/produtos/oferta")
	public Produto aceitaOferta(@RequestBody @Valid OfertaDTO ofertaDTO) {
		if(ps.obtemProdutoporId(ofertaDTO.getId()) == null) {
			throw new EntityNotFoundException("O produto não está cadastrado na base de dados.");
		}
		ps.checaData(ofertaDTO.getId());
		ps.deadEndOferta(ofertaDTO.getId());
		ps.aumentaPorOferta(ofertaDTO.getId(), ofertaDTO.getOferta());
		return ps.obtemProdutoporId(ofertaDTO.getId());
	}
	
	@PostMapping(value = "/produtos/incremento")
	public Produto aceitaIncremento(@RequestBody @Valid IncrementoDTO incrementoDTO) {
		if(ps.obtemProdutoporId(incrementoDTO.getId()) == null) {
			throw new EntityNotFoundException("O produto não está cadastrado na base de dados.");
		}
		ps.checaData(incrementoDTO.getId());
		ps.deadEndOferta(incrementoDTO.getId());
		ps.aumentaPorIncremento(incrementoDTO.getId());
		return ps.obtemProdutoporId(incrementoDTO.getId());
	}
	
	@DeleteMapping(value = "/produtos/{id}")
	public String deletaProduto(@RequestBody @Valid @PathVariable("id") ProdutoDTO produtoDTO ) {
		if(ps.obtemProdutoporId(produtoDTO.getId()) == null) {
			throw new EntityNotFoundException("O produto " + produtoDTO.getNome() + " já não existe na base de dados.");
		}
		ps.deletaProdutos(produtoDTO.getId());
		return "Produto deletado do banco de dados.";
	}
		
}
