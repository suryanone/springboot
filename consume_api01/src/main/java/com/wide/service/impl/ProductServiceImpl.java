package com.wide.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wide.dao.ProductDao;
import com.wide.dao.RepositoryException;
import com.wide.model.Product;
import com.wide.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> productList() throws RepositoryException {
		return productDao.productList();
	}

	@Override
	public void addProduct(Product product) throws RepositoryException {
		productDao.addProduct(product);
	}

	@Override
	public void updateProduct(Product product) throws RepositoryException {
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int idProduct) throws RepositoryException {
		productDao.deleteProduct(idProduct);
	}

}
