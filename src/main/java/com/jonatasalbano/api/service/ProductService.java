package com.avenuecode.AvenueTask.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.avenuecode.AvenueTask.dao.ImageDAO;
import com.avenuecode.AvenueTask.dao.ProductDAO;
import com.avenuecode.AvenueTask.model.Image;
import com.avenuecode.AvenueTask.model.Product;
import com.avenuecode.AvenueTask.rest.model.ImageREST;
import com.avenuecode.AvenueTask.rest.model.ImageWrapped;
import com.avenuecode.AvenueTask.rest.model.ProductREST;
import com.avenuecode.AvenueTask.rest.model.ProductWrapped;
import com.avenuecode.AvenueTask.rest.util.RestUtil;

@Path("api")
public class ProductService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/products")
    public ProductWrapped justProducts() {
		ProductDAO productDAO = new ProductDAO();
		List<ProductREST> productsRest = new ArrayList<>();
		for(Product product : productDAO.getAllProducts()) {
			productsRest.add(new ProductREST(product, false));
		}
		ProductWrapped productWrapped = new ProductWrapped();
    	productWrapped.setProducts(productsRest);
    	return productWrapped;    
    }
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list/products/relationships")
    public ProductWrapped getIt() {
		ProductDAO productDAO = new ProductDAO();
		List<ProductREST> productsRest = new ArrayList<>();
		for(Product product : productDAO.getAllProducts()) {
			productsRest.add(new ProductREST(product, true));
		}
		ProductWrapped productWrapped = new ProductWrapped();
    	productWrapped.setProducts(productsRest);
    	return productWrapped;    
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product")
    public ProductWrapped justProductById(@QueryParam("id") Long id) {
    	ProductDAO productDAO = new ProductDAO();
    	ProductREST product = new ProductREST(productDAO.getProductById(id), false);
    	List<ProductREST> listProduct = new ArrayList<>();
    	listProduct.add(product);
		ProductWrapped productWrapped = new ProductWrapped();
		productWrapped.setProducts(listProduct);
    	return productWrapped;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/relationships")
    public ProductWrapped getProduct(@QueryParam("id") Long id) {
    	ProductDAO productDAO = new ProductDAO();
    	ProductREST product = new ProductREST(productDAO.getProductById(id), true);
    	List<ProductREST> listProduct = new ArrayList<>();
    	listProduct.add(product);
		ProductWrapped productWrapped = new ProductWrapped();
		productWrapped.setProducts(listProduct);
    	return productWrapped;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/images")
    public ImageWrapped imagesByProduct(@QueryParam("id") Long id) {
    	ImageDAO imageDAO = new ImageDAO();
    	RestUtil restUtil = new RestUtil();
    	List<ImageREST> listImages = restUtil.listImageToImageREST(imageDAO.getImagesByProductId(id));
		ImageWrapped imageWrapped = new ImageWrapped();
		imageWrapped.setImages(listImages);
    	return imageWrapped;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/product/childproducts")
    public ProductWrapped getChildProduct(@QueryParam("id") Long id) {
    	ProductDAO productDAO = new ProductDAO();
    	RestUtil restUtil = new RestUtil();
    	List<ProductREST> listProduct = restUtil.listProductToProductREST(productDAO.getChildProducts(id));
    	ProductWrapped productWrapped = new ProductWrapped();
		productWrapped.setProducts(listProduct);
    	return productWrapped;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createProduct")
    public Product createProduct(Product product) {
    	ProductDAO productDAO = new ProductDAO();
    	for(Image image : product.getImages()) {
    		image.setIdProductParent(productDAO.getLastIdUsed());
    	}
    	productDAO.insertProduct(product);
    	return product;
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("updateProduct/{id}")
    public Product updateProduct(@PathParam("id")Long id, Product product) {
    	ProductDAO productDAO = new ProductDAO();
    	product.setId(id);
    	productDAO.updateProduct(product);
    	return product;
    }
    
    @DELETE 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deleteProduct/{id}")
    public void deleteProduct(@PathParam("id") Long id) {
    	ProductDAO productDAO = new ProductDAO();
    	Product product = productDAO.getProductById(id);
    	productDAO.deleteProduct(product);
    }
}
