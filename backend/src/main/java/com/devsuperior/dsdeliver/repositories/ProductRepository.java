package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Product;

// CAMADA DE ACESSO A DADOS (REPOSITORY)
// JpaRepository eh uma interface q faz parte do sub framework data.jpa q traz umas implementações padrao pra salvar buscar, deletar e atualizar dados da entidade q qro (1o param dele a entidade)
public interface ProductRepository extends JpaRepository<Product, Long>{
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods (table 3). Isso sao comandos q o SpringBoot da pra n precisar escrever a consulta SQL
	List<Product> findAllByOrderByNameAsc();
	
}
