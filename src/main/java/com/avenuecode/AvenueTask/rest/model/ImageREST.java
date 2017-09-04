package com.avenuecode.AvenueTask.rest.model;

import com.avenuecode.AvenueTask.model.Image;

public class ImageREST {
	private Long id;
	private String type;
	
	public ImageREST(Image image) {
		id = image.getId();
		type = image.getType();
	}
	
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
