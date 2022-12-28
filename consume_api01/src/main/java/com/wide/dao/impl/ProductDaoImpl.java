package com.wide.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.wide.dao.ProductDao;
import com.wide.dao.RepositoryException;
import com.wide.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> productList() throws RepositoryException {
		List<Product> products = new ArrayList<Product>();
		try {
			URL url = new URL("http://localhost:8989/products");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Check if connect has been made
			int responseCode = conn.getResponseCode();

			// 200 OK
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
				//execute request using bufferedreader and the output is string
				InputStream inputStream = conn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null) {
					buffer.append(line);
				}

				// JSON simple library setup with maven to convert string to JSON
				JSONParser parse = new JSONParser();
				// The value of informationString must array if use JSONArray
				JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(buffer));

				for (int i = 0; i < dataObject.size(); i++) {
					JSONObject productData = (JSONObject) (dataObject.get(i));
					int productId = ((Number) productData.get("productId")).intValue();
					String code = (String) productData.get("code");
					String name = (String) productData.get("name");
					String type = (String) productData.get("type");
					Double price = (Double) productData.get("price");
					Product product = new Product(productId, code, name, type, price);
					products.add(product);
				}

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public void addProduct(Product product) throws RepositoryException {
		try {
			URL url = new URL("http://localhost:8989/products");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
//			String jsonBody = "{code:'" + product.getCode() + "',name:'" + product.getName() + "',type:'" + product.getType() + "',price:'" + product.getPrice() + "'}";
			String jsonBody = "{\"code\":\" " + product.getCode() + "\",\"name\":\"" + product.getName()
					+ "\",\"type\":\"" + product.getType() + "\",\"price\":" + product.getPrice() + "}";
//			String jsonBody = "{\"code\":\'" + product.getCode() 
//			+ "\"',\"name\":\'" + product.getName() 
//			+ "\"',\"type\":\'" + product.getType() 
//			+ "\"',\"price\":" + product.getPrice() + "}";
			// execute the request
			byte[] inputJson = jsonBody.getBytes();
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(inputJson);
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(Product product) throws RepositoryException {
		try {
			URL url = new URL("http://localhost:8989/products/" + product.getProductId() +"");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonBody = "{\"code\":\" " + product.getCode() + "\",\"name\":\"" + product.getName()
			+ "\",\"type\":\"" + product.getType() + "\",\"price\":" + product.getPrice() + "}";
			// execute the request
			byte[] inputJson = jsonBody.getBytes();
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(inputJson);
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(int idProduct) throws RepositoryException {
		// API:
		// http://localhost:8989/orders
		// http://localhost:8989/products
		try {
			URL url = new URL("http://localhost:8989/products/" + idProduct + "");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");

			// execute the request
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuffer buffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
