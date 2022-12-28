package com.wide.spring_boot.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wide.spring_boot.dao.ProductDao;

@Controller
public class ViewController {
	
	@Autowired
	ProductDao productDao;
	
	@GetMapping("/hello")
	public String hello( ) {
		
		return "Hello";
	}
	//jsp
	@GetMapping("/")
	public String home( ) {
		
		return "index";
	}
	
//	@GetMapping("/listProduct")
//	public String listProduct(Map<String, Object> map) {
//		map.put("productList", productDao.findAll());
//		return "listProduct";
//	}
	
	//thymeleaf
//	@GetMapping("/index")
//	public String homeThymeleaf() {
//	    return "index";
//	}
}
