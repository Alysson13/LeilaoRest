package br.com.adal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adal.entidade.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{

}
