package com.wide.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wide.dao.RepositoryException;
import com.wide.model.Product;
import com.wide.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/listProduct")
	public String listProduct(Map<String, Object> map) {
		try {
			map.put("productList", productService.productList());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return "listProduct";
	}
	
	@RequestMapping("/addProduct")
	public String toAddProduct(ModelMap map) {
		map.addAttribute("product", new Product());
		return "formAddProduct";
	}
	
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, BindingResult result) {
		if(null != product) {
			try {
				productService.addProduct(product);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/listProduct";
	}
	
	@RequestMapping("/delete/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId) {
		try {
			productService.deleteProduct(productId);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return "redirect:/listProduct";
	}
	
	@RequestMapping("/update/{productId}")
	public String toUpdateProduct(@PathVariable("productId") int productId, ModelMap map) {
		map.addAttribute("product", new Product());
		return "formUpdateProduct";
	}
	
	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, BindingResult result) {
		if(null != product) {
			try {
				productService.updateProduct(product);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/listProduct";
	}
}
