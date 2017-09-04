package com.avenuecode.AvenueTask.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.avenuecode.AvenueTask.model.Image;
import com.avenuecode.AvenueTask.model.Product;

public class ImageDAO {
	EntityManagerFactory emf;
	EntityManager em;
	
	public ImageDAO() {
		emf = Persistence.createEntityManagerFactory("persistence-unit");
		em = emf.createEntityManager();
	}
	
	public void insertProduct(Image image) {
		em.getTransaction().begin();
		em.persist(image);
		em.getTransaction().commit();	
	}
	
	public List<Image> getAllImages() {
		em.getTransaction().begin();
		List<Image> resultList = em.createQuery("FROM Image", Image.class).getResultList();
		return resultList;
		
	}
	
	public Image getImageById(Long id) {
		Image image = em.find(Image.class, id);
		return image;
	}
	
	public List<Image> getImagesByProductId(Long productId) {
		return em.createQuery("From Image WHERE product_id = :idProduct", Image.class)
						.setParameter("idProduct", productId)
						.getResultList();
	}
}
