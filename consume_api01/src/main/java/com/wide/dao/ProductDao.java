package com.wide.dao;

import java.util.List;

import com.wide.model.Product;

public interface ProductDao {
	List<Product> productList() throws RepositoryException;
	void addProduct(Product product) throws RepositoryException;
	void updateProduct(Product product) throws RepositoryException;
	void deleteProduct(int idProduct) throws RepositoryException;
}
