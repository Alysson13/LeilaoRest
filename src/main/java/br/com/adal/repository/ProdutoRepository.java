package br.com.adal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.adal.entidade.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>{
	
	public Produto findByNomeIgnoreCase(String nome);
	public void deleteByNomeIgnoreCase(String nome);
}
