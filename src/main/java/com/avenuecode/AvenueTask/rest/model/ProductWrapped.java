package com.avenuecode.AvenueTask.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.avenuecode.AvenueTask.model.Product;

@XmlRootElement
public class ProductWrapped {

	List<ProductREST> products;

	public List<ProductREST> getProducts() {
		return products;
	}

	public void setProducts(List<ProductREST> products) {
		this.products = products;
	}
}
