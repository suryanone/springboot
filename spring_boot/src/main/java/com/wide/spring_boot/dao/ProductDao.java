package com.wide.spring_boot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wide.spring_boot.model.Product;

public interface ProductDao extends CrudRepository<Product, Integer> {
	List<Product> findAll();
	Product findById(int productId);
}
