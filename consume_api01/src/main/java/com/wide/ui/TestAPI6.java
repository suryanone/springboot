package com.wide.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestAPI6 {

	public static void main(String[] args) {

		// API:
		// http://localhost:8989/orders
		// http://localhost:8989/products

		try {
			URL url = new URL("http://localhost:8989/orders");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			String jsonBody = "[{\"price\":25000.0,\"quantity\":1,\"productIdFk\":33},{\"price\":20000.0,\"quantity\":2,\"productIdFk\":44}]";
			byte[] inputJson = jsonBody.getBytes();
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(inputJson);
			System.out.println("Response code: " + conn.getResponseCode());
//			System.out.println("Response message: " + conn.getResponseMessage());
			
			//execute the request
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			StringBuffer buffer = new StringBuffer();
			while((line = bufferedReader.readLine()) != null) {
				buffer.append(line);
			}
			System.out.println(buffer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
