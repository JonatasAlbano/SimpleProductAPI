package com.avenuecode.AvenueTask.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	
	@OneToMany(mappedBy="product", cascade = CascadeType.ALL)
	@JsonInclude(Include.NON_NULL)
	private List<Image> images = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "parent_product_id")
	private Product parentProduct;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
    public void removeComment(Image image) {
        images.remove(image);
        image.setProduct(null);
    }
	public void setProduct(Product product) {
		this.parentProduct = product;
	}
	public Product getParentProduct() {
		return parentProduct;
	}
	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", image=" + images + ", parentProduct="
				+ parentProduct + "]";
	}
}
