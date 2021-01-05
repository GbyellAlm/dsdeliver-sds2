package com.devsuperior.dsdeliver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Classe Product eh uma entidade q vai ser gerenciada pelo ORM. Feita essa anotacao essa classe pode virar uma tabela no banco dai.
@Entity
//Especificando o nome da tabela do banco
@Table(name = "tb_product")

// Serializable eh uma boa medida pra q os objetos Product possam ser transformados em uma sequencia de bytes. (Era um padrao antigo do Java pra evitar q o objeto desse problema na rede)   
public class Product implements Serializable{
	
	// serialVersionUID significa q essa implementacao da classe eh a versao 1
	private static final long serialVersionUID = 1L;
	
	// Especificando qm eh a chave primaria
	@Id
	// Especificando pra criar a chave primaria como auto incrementavel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String imageUri;
	
	// Eh necessario um construtor s/ argumentos
	public Product() {
		
	}

	public Product(Long id, String name, Double price, String description, String imageUri) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUri = imageUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	
	// Pra comparar um produto c/ o outro (linhas 65 e 73)
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
