package com.avenuecode.AvenueTask.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.avenuecode.AvenueTask.model.Image;
import com.avenuecode.AvenueTask.model.Product;
import com.avenuecode.AvenueTask.rest.util.RestUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlRootElement
public class ProductREST {

	private Long id;
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	private List<ImageREST> images = new ArrayList<>();
	@JsonInclude(Include.NON_NULL)
	private ProductREST parentProduct;
	
	public ProductREST(Product product, boolean relationships) {
		this.id = product.getId();
		this.description = product.getDescription();
		if(relationships) {
			RestUtil restUtil = new RestUtil();
			this.images = restUtil.listImageToImageREST(product.getImages());
			if(product.getParentProduct() != null)
				this.parentProduct = new ProductREST(product.getParentProduct(), false);
			clearProductParentImages();
		} else {
			this.images = null;
		}
	}
	
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
	public void setProduct(ProductREST product) {
		this.parentProduct = product;
	}
	public ProductREST getParentProduct() {
		return parentProduct;
	}
	public void setParentProduct(ProductREST parentProduct) {
		this.parentProduct = parentProduct;
	}
	public List<ImageREST> getImages() {
		return images;
	}
	public void setImages(List<ImageREST> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", image=" + images + ", parentProduct="
				+ parentProduct + "]";
	}
	
	public void clearProductParentImages() {
		if(parentProduct != null) {
			parentProduct.setImages(null);
		}
	}
}
