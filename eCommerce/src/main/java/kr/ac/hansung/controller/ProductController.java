package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.entity.Product;
import kr.ac.hansung.exception.NotFoundException;
import kr.ac.hansung.service.ProductService;
import lombok.Getter;
import lombok.Setter;

/*
URL: /api/products

To get list of products:  GET "http://localhost:8080/ecommerce/api/products"
To get products info:  GET "http://localhost:8080/ecommerce/api/products/{id}"
To create products:   POST "http://localhost:8080/ecommerce/api/products" -d '{ "name": "P1", "price": 100.00 }'
To update products:   PUT "http://localhost:8080/ecommerce/api/products/{id}" -d '{ "name": "P1", "price": 100.00 }'
To delete products: DELETE "http://localhost:8080/ecommerce/api/products/{id}"
*/

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAllProducts() {
		
		// Getting all products in application...
		final List<Product> products = productService.getAllProducts();

		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> retrieveProduct(@PathVariable Long id) {

		
	}

	// DTO(Data Transfer Object) : 계층간 데이터 교환을 위한 객체, 여기서는 클라이언트(Postman)에서 오는 데이터를 수신할 목적으로 사용
    // Product와 ProductDto와의 차이를 비교해서 살펴보기 바람
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDto request) {

		Product product = productService.createProduct(request.getName(), request.getPrice());

		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
    
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto request) {
		
		
		
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		// Getting the requiring product; or throwing exception if not found
		final Product product = productService.getProductById(id);
		
		if(product == null)
			throw new NotFoundException(id);						

		// Deleting product from the application...
		productService.deleteProduct(product);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //아래와 동일한 기능
		//return ResponseEntity.noContent().build();

	}
	
	@Getter
	@Setter
	static class ProductDto {
		
        @NotNull(message = "name is required")
        @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
        private String name;           
        
        @NotNull(message = "name is required")
        @Min(0)
        private Double price;
	}
}