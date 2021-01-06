package com.devsuperior.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entities.Order;

// CAMADA DE ACESSO A DADOS (REPOSITORY)
// JpaRepository eh uma interface q faz parte do sub framework data.jpa q traz umas implementações padrao pra salvar buscar, deletar e atualizar dados da entidade q qro (1o param dele a entidade)
public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
