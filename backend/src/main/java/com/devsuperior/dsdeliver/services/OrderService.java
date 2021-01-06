package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.OrderDTO;
import com.devsuperior.dsdeliver.dto.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepository;
import com.devsuperior.dsdeliver.repositories.ProductRepository;

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
	
	@Autowired
	private ProductRepository productRepository;
	
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
	
	// Salvando pedidos
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		// Associando os produtos aos pedidos
		for (ProductDTO p: dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		// Dps de associar, salvar os pedidos no banco
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
	// Atualizando o status dos pedidos pra entregue
	@Transactional
	public OrderDTO setDelivered(Long id) {
		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		order = repository.save(order);
		return new OrderDTO(order);
	}
	
}
