package com.devsuperior.dsdeliver.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

// Classe Order eh uma entidade q vai ser gerenciada pelo ORM. Feita essa anotacao essa classe pode virar uma tabela no banco dai.
@Entity
// Especificando o nome da tabela do banco
@Table(name = "tb_order")
public class Order implements Serializable{
	// Especificando qm eh a chave primaria
	@Id
	// Especificando pra criar a chave primaria como auto incrementavel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;
	
	// Esse ManyToMany eh do Set tb. Ele diz q varios produtos podem ter varios pedidos
	@ManyToMany
	// Declarando pro sistema a tabela "tb_order_product"
	// @JoinTable eh uma notacao pra dizer os parametros da tabela q qro criar
	@JoinTable(name = "tb_order_product", 
		joinColumns = @JoinColumn(name = "order_id"),
		inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	// Pra fazer a relação entre Order e Product e evitar que ocorra repeticao de produto no pedido. Set eh uma colecao e ela n permite a ocorrencia de repeticoes. O uso do Set permite q qdo for feito o mapeamento objeto-relacional, o JPA (ORM do Java) cria a relacao de muitos pra muitos ja  
	// HashSet<>() eh uma implementacao do set
	private Set<Product> products = new HashSet<>();
	
	// Eh necessario um construtor s/ argumentos
	public Order() {
		
	}
	
	// Nesse caso n se usa a colecao "products" pra criar o construtor de argumentos
	public Order(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status) {
		super();
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	public Double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Instant getMoment() {
		return moment;
	}
	
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	// N tem o setProducts() pq nesse caso n se vai trocar a colecao em momento algum. Soh criar e atualizar.
	public Set<Product> getProducts() {
		return products;
	}
	
	// hashCode e equals = msm situacao do q foi feito em Product
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
