package com.wide.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import com.wide.dao.OrderDao;
import com.wide.dao.RepositoryException;
import com.wide.model.Order;
import com.wide.model.OrderItem;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Override
	public void save(Order order) throws RepositoryException {
		try {
			URL url = new URL("http://localhost:8989/orders");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			// add multiple orderItem to array list String
			List<String> jsonBodies = new ArrayList<String>();
			for (OrderItem orderItem : order.getOrderItems()) {
				String jsonBody = "{\"price\":" + orderItem.getPrice() + ",\"quantity\":" + orderItem.getQuantity()
						+ ",\"productIdFk\": " + orderItem.getProductIdFk() + "}";
				jsonBodies.add(jsonBody);
			}
			// convert array list String to String using StringBuffer
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < jsonBodies.size(); i++) {
				sb.append(jsonBodies.get(i));
				if (i < (jsonBodies.size() - 1)) {
					sb.append(",");
				}
			}
			// fix format string to match the condition when push using JSON text
			String jsonFix = "[" + sb.toString() + "]";
			// execute the request
			byte[] inputJson = jsonFix.getBytes();
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
	public List<Order> orderList() throws RepositoryException {
		List<Order> orders = new ArrayList<Order>();
		try {
			URL url = new URL("http://localhost:8989/orders");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			// Check if connect has been made
			int responseCode = conn.getResponseCode();

			// 200 OK
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
				// execute request using bufferedreader and the output is string
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
					JSONObject orderData = (JSONObject) (dataObject.get(i));
					int orderId = ((Number) orderData.get("id")).intValue();
					Date temp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS")
							.parse((String) orderData.get("createdAt"));
					List<OrderItem> orderItems = (List<OrderItem>) orderData.get("orderItems");
					Calendar cal = Calendar.getInstance();
					cal.setTime(temp);
					cal.add(Calendar.HOUR_OF_DAY, 7);
					Date orderDate = cal.getTime();
					Order order = new Order(orderId, orderDate, orderItems);
					orders.add(order);
				}

			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order findOrderById(int orderId) throws RepositoryException {
		Order order = new Order();
		try {
			URL url = new URL("http://localhost:8989/orders/" + orderId);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
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
			JSONObject orderData = (JSONObject) parse.parse(String.valueOf(buffer));
			Date temp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+SSSS").parse((String) orderData.get("createdAt"));
			List<OrderItem> orderItems = (List<OrderItem>) orderData.get("orderItems");
			Calendar cal = Calendar.getInstance();
			cal.setTime(temp);
			cal.add(Calendar.HOUR_OF_DAY, 7);
			Date orderDate = cal.getTime();
			order = new Order(orderId, orderDate, orderItems);
			
			
		}catch(

	MalformedURLException e)
	{
		e.printStackTrace();
	}catch(
	IOException e)
	{
		e.printStackTrace();
	}catch(
	java.text.ParseException e)
	{
		e.printStackTrace();
	}catch(
	ParseException e)
	{
			e.printStackTrace();
		}return order;
}}
