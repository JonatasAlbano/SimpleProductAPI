package com.avenuecode.AvenueTask;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.avenuecode.AvenueTask.dao.ImageDAO;
import com.avenuecode.AvenueTask.dao.ProductDAO;
import com.avenuecode.AvenueTask.model.Image;
import com.avenuecode.AvenueTask.model.Product;

public class AvenueTask {
	public static void main(String[] args) {
		
		Product product = new Product();
		product.setDescription("Product One");
		List<Image> images = new ArrayList<Image>();
		Image image = new Image();
		image.setType("Image of Product One");
		Image image2 = new Image();
		image2.setType("Image 2 of Product One");
		
		image.setProduct(product);
		image2.setProduct(product);
		images.add(image2);
		images.add(image);
		product.setImages(images);

		ProductDAO productDAO = new ProductDAO();
		productDAO.insertProduct(product);
		
		Product product2 = new Product();
		product2.setDescription("Product Two");
		product2.setParentProduct(productDAO.getProductById(1L));
		productDAO.insertProduct(product2);
		
		Product product3 = new Product();
		product3.setDescription("Product Three");
		product3.setParentProduct(productDAO.getProductById(2L));
		List<Image> imagesList = new ArrayList<Image>();
		Image image3 = new Image();
		image3.setType("Image of product Three");
		image3.setProduct(product3);
		imagesList.add(image3);
		productDAO.insertProduct(product3);
		
	}
}
