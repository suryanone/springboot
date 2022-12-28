package com.wide.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.wide.model.Product;

@Controller
public class ProductController {
	
	private static final String GET_PRODUCTS_ENDPOINT_URL = "http://localhost:8989/products";
    private static final String GET_PRODUCT_ENDPOINT_URL = "http://localhost:8989/products/{id}";
    private static final String CREATE_PRODUCT_ENDPOINT_URL = "http://localhost:8989/products";
    private static final String UPDATE_PRODUCT_ENDPOINT_URL = "http://localhost:8989/products/{productId}";
    private static final String DELETE_PRODUCT_ENDPOINT_URL = "http://localhost:8989/products/{productId}";
    private static RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	
	@GetMapping("/listProduct")
	public String listProduct(Model map) {
		ResponseEntity<List<Product>> response = restTemplate.exchange(GET_PRODUCTS_ENDPOINT_URL, HttpMethod.GET, null,  new ParameterizedTypeReference<List<Product>>() {});
		List<Product> products = response.getBody();
		map.addAttribute("productList", products);
		return "listProduct";
	}
	
	@GetMapping("/listProduct/add")
	public String toAddProduct(Model map) {
		map.addAttribute("product", new Product());
		return "formAddProduct";
	}
	
	@PostMapping(value = "/addProduct")
	public String addProduct(@ModelAttribute("product") Product product) {
		restTemplate.postForEntity(CREATE_PRODUCT_ENDPOINT_URL, product, Product.class);
		return "redirect:/listProduct";
	}
	
	@GetMapping(value = "/delete/{productId}")
	public String deleteProduct(@PathVariable("productId") String productId) {
		Map< String, String > params = new HashMap< String, String > ();
		params.put("productId", productId);
		restTemplate.delete(DELETE_PRODUCT_ENDPOINT_URL, params);
		return "redirect:/listProduct";
	}
	
	@GetMapping("/update/{productId}")
	public String toUpdateProduct(@PathVariable("productId") int productId, Model map) {
		map.addAttribute("product", new Product());
		return "/formUpdateProduct";
	}
	
	@PostMapping("/product/update")
	public String updateProduct(@ModelAttribute("product") Product product, String productId, BindingResult result) {
		Map< String, String > params = new HashMap< String, String > ();
		params.put("productId", productId);
		restTemplate.put(UPDATE_PRODUCT_ENDPOINT_URL, product, params);
		return "redirect:/listProduct";
	}
	
	@GetMapping("/updatePrice/{productId}/{price}")
	public String toUpdatePriceProduct(@PathVariable("productId") int productId, @PathVariable("price") double price, Model map) {
		map.addAttribute("product", new Product());
		return "/formUpdatePriceProduct";
	}
	
	@PostMapping("/product/updatePrice")
	public String updatePriceProduct(@ModelAttribute("product") Product product, String productId, BindingResult result) {
		//HttpComponentsClientHttpRequestFactory needed for HTTP PATCH
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(requestFactory);
		
		Map< String, String > params = new HashMap< String, String > ();
		params.put("productId", productId);
		restTemplate.patchForObject(UPDATE_PRODUCT_ENDPOINT_URL, product, Product.class, params);
		return "redirect:/listProduct";
	}
}
