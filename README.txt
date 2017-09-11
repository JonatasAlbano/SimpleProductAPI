To generate data to testing, the command "mvn exec:java" should be used.
To run the API service, the command "mvn tomcat:run" should be used.
The database (HSQL) is predefined at "C:/banco/teste". To use another directory, change the "persistence.xml" file in the "javax.persistence.jdbc.url" property value.
About the calls:
1 - All products without relationships (GET) http://localhost:8080/AvenueTask/rest/api/list/products
2 - All products and relationships (GET) http://localhost:8080/AvenueTask/rest/api/list/products/relationships
3 - Specific product without relationships (GET) http://localhost:8080/AvenueTask/rest/api/product?id=(product id)
4 - Specific product and relationships (GET) http://localhost:8080/AvenueTask/rest/api/product/relationships?id=(product id)
5 - Set of child products for specific product (GET) http://localhost:8080/AvenueTask/rest/api/product/childproducts?id=(product id)
6 - Set of images for specific product (GET) http://localhost:8080/AvenueTask/rest/api/product/images?id=(product id)
7 - Delete a product (DELETE) http://localhost:8080/AvenueTask/rest/api/deleteProduct/(product id)
8 - Create product (POST) http://localhost:8080/AvenueTask/rest/api/createProduct JSON example: { "description": "Produto 4", "images": [ { "type": "NovaImagem" }, { "type": "NovaImagem 2" } ], "parentProduct": { "id": 2 } }
9 - Update product by id (PUT) http://localhost:8080/AvenueTask/rest/api/updateProduct/(id) JSON example: { "description": "Produto 1" }
The front end part was made directly on the view due to lack of time:
View that consumes the products and relationships: http://localhost:8080/AvenueTask/products_relationships.html

View that consumes the products without relationships: http://localhost:8080/AvenueTask/products.html

View to post a product with a image: http://localhost:8080/AvenueTask/post_product.html
