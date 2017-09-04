package com.avenuecode.AvenueTask.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
public class Image {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String type;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	@JoinColumn(name="product_id")
	private Product product;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", type=" + type + ", product=" + "]";
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public void setIdProductParent(Long id) {
		this.product = new Product();
		this.product.setId(++id);
	}

//	@Override
//	    public boolean equals(Object o) {
//	        if (this == o) return true;
//	        if (!(o instanceof Image )) return false;
//	        return id != null && id.equals(((Image) o).id);
//	    }
//	    @Override
//	    public int hashCode() {
//	        return 31;
//	    }
}
