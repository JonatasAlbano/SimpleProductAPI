package com.avenuecode.AvenueTask.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.avenuecode.AvenueTask.model.Product;

public class ProductDAO {
	EntityManagerFactory emf;
	EntityManager em;
	
	public ProductDAO() {
		emf = Persistence.createEntityManagerFactory("persistence-unit");
		em = emf.createEntityManager();
	}
	
	public List<Product> getAllProducts() {
		em.getTransaction().begin();
		List<Product> resultList = em.createQuery("FROM Product", Product.class).getResultList();
		return resultList;
		
	}
	public Product getProductById(Long id) {
		Product product = em.find(Product.class, id);
		return product;
	}
	
	public void insertProduct(Product product) {
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();	
		em.clear();
	}
	
	public void updateProduct(Product product) {
		em.getTransaction().begin();
		em.merge(product);
		em.getTransaction().commit();	
		em.clear();
	}
	
	public void deleteProduct(Product product) {
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
		em.clear();
	}
	
	public Long getLastIdUsed() {
		String query = "SELECT MAX(id) FROM Product";
		return (Long) em.createQuery(query).getSingleResult();
	}
	
	public List<Product> getChildProducts(Long parentId) {
		return em.createQuery("From Product WHERE parent_product_id = :parentId", Product.class)
				.setParameter("parentId", parentId)
				.getResultList();
	}
}
