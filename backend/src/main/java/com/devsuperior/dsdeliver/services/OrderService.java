package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.repositories.OrderRepository;

// @Service pra q essa classe possa conversar c/ os outros componentes (Controller e Repository)
@Service
public class OrderService {
	
	// @Autowired eh pra marcar dependencia entre o Service e o Repository. Podia fazer igual no exemplo abaixo, mas o framework tem essa anotacao q ja faz isso
	@Autowired
	private OrderRepository repository;
	
	/*
	 * Exemplo de injecao de dependencia desacoplada
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	*/
	
	// @Transactional = Coisa de banco de dados. Conf especifica de banco de dados msm
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		// Buscando os produtos do Repository (banco de dados)
		List<Order> list = repository.findOrdersWithProducts();
		
		// Transformando a lista Order pra OrderDTO
		// Map aqui aplica uma funcao em tdos os elementos da lista transformando esses elementos em uma nova lista
		// .collect(Collectors.toList() = Reconvertendo o stream pra uma lista dnv pq eu tinha convertido a lista pra stream anteriormente 
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
