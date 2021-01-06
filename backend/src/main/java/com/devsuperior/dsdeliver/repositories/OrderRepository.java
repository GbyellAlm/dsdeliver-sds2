package com.devsuperior.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsdeliver.entities.Order;

// CAMADA DE ACESSO A DADOS (REPOSITORY)
// JpaRepository eh uma interface q faz parte do sub framework data.jpa q traz umas implementações padrao pra salvar buscar, deletar e atualizar dados da entidade q qro (1o param dele a entidade)
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	// Retorna somente os pedidos pendentes e q estejam ordenados do mais antigo pro mais recente
	// @Query = Consulta SQL no JPA. Essa consulta n eh escrita em SQL e sim em JPQL (linguagem SQL do JPA)
	// Buscar os pedidos c/ seus registros correspondentes = JOIN FETCH
	// obj.products = Esse "products" vem da colecao de produtos da classe Order (linha 44 da classe Order)
	@Query("SELECT DISTINCT obj FROM Order obj JOIN FETCH obj.products" + " WHERE obj.status = 0 ORDER BY obj.moment ASC")
	List<Order> findOrdersWithProducts(); 
}
