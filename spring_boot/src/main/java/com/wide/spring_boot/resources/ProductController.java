package com.wide.spring_boot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wide.spring_boot.dao.ProductDao;
import com.wide.spring_boot.model.Product;

@RestController
public class ProductController {
	
	@Autowired
	ProductDao productDao;
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		int numberHttpStatus = 0; 
		if(product != null && product.getCode() != "") {
			product = productDao.save(product);
			numberHttpStatus = 201;
		} else {
			product = null;
			numberHttpStatus = 400;
		}
		return new ResponseEntity<Product>(product, HttpStatus.valueOf(numberHttpStatus));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getListProducts() {
		int numberHttpStatus = 0;
		List<Product> products = null;
		if(productDao.findAll() != null) {
			products = productDao.findAll();
			numberHttpStatus = 200;
		} else {
			numberHttpStatus = 404;
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.valueOf(numberHttpStatus));
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> findProductById(@PathVariable("productId") int productId) {
		int numberHttpStatus = 0;
		Product product = productDao.findById(productId);
			if(product != null) {
				numberHttpStatus = 200;
			} else {
				numberHttpStatus = 404;
			}
		
		return new ResponseEntity<Product>(product, HttpStatus.valueOf(numberHttpStatus));
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("productId") int productId) {
		int numberHttpStatus = 0;
		Product product = null;
		if(productDao.findById(productId) != null) {
			product = productDao.findById(productId);
			productDao.deleteById(productId);
			numberHttpStatus = 200;
		} else {
			numberHttpStatus = 404;
		}
		return new ResponseEntity<Product>(product, HttpStatus.valueOf(numberHttpStatus));
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProductById(@RequestBody Product product, @PathVariable("productId") int productId) {
		int numberHttpStatus = 0;
		Product prevProduct = productDao.findById(productId);
		if(prevProduct != null) {
			prevProduct.setProductId(productId);
			prevProduct.setCode(product.getCode());
			prevProduct.setName(product.getName());
			prevProduct.setPrice(product.getPrice());
			prevProduct.setType(product.getType());
			productDao.save(prevProduct);
			numberHttpStatus = 200;
		} else {
			numberHttpStatus = 404;
		}
		
		return new ResponseEntity<Product>(prevProduct, HttpStatus.valueOf(numberHttpStatus));
	}
	
	@PatchMapping("/products/{productId}")
	public ResponseEntity<Product> updatePriceProductById(@RequestBody Product product, @PathVariable("productId") int productId) {
		Product prevProduct = productDao.findById(productId);
		int numberHttpStatus = 0;
		if(prevProduct != null) {
			prevProduct.setPrice(product.getPrice());
			productDao.save(prevProduct);
			numberHttpStatus = 200;
		}else {
			numberHttpStatus = 404;
		}
	
		return new ResponseEntity<Product>(prevProduct, HttpStatus.valueOf(numberHttpStatus));
	}
}
