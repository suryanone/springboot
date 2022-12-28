package com.wide.service;

import java.util.List;

import com.wide.dao.RepositoryException;
import com.wide.model.Product;

public interface ProductService {
	public List<Product> productList() throws RepositoryException;
	public void addProduct(Product product) throws RepositoryException;
	public void updateProduct(Product product) throws RepositoryException;
	public void deleteProduct(int idProduct) throws RepositoryException;
}
