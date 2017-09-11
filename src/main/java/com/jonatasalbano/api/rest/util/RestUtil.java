package com.avenuecode.AvenueTask.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.avenuecode.AvenueTask.model.Image;
import com.avenuecode.AvenueTask.model.Product;
import com.avenuecode.AvenueTask.rest.model.ImageREST;
import com.avenuecode.AvenueTask.rest.model.ProductREST;

public class RestUtil {

	public List<ImageREST> listImageToImageREST(List<Image> images) {
		List<ImageREST> imagesRest = new ArrayList<>();
		for(Image image : images) {
			imagesRest.add(new ImageREST(image));
		}
		return imagesRest;
	}
	
	public List<ProductREST> listProductToProductREST(List<Product> products) {
		List<ProductREST> productREST = new ArrayList<>();
		for(Product product : products) {
			productREST.add(new ProductREST(product, false));
		}
		return productREST;
	}
}
