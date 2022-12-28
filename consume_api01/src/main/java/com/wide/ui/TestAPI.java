package com.wide.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestAPI {

	public static void main(String[] args) {

		// API:
		// http://localhost:8989/orders
		// http://localhost:8989/products
		try {
			URL url = new URL("http://localhost:8989/products");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			//Check if connect has been made
			int responseCode = conn.getResponseCode();
			
			//200 OK
			if(responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
				//execute the request using bufferedreader and the output is string
				InputStream inputStream = conn.getInputStream();
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while((line = bufferedReader.readLine()) != null) {
					buffer.append(line);
				}
				System.out.println(buffer);
				//show the product using string builder and scanner and the output is string
//				StringBuilder informationString = new StringBuilder();
//				Scanner scanner = new Scanner(url.openStream());
//				
//				while (scanner.hasNext()) {
//					informationString.append(scanner.nextLine());
//				}
//				scanner.close();
//				//string output
//				System.out.println(informationString);
				
				//JSON simple library setup with maven to convert string to JSON
				JSONParser parse = new JSONParser();
				//The value of informationString must array if use JSONArray
				JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(buffer));
				System.out.println(dataObject.get(0));
				
//				JSONObject productData1 = (JSONObject) dataObject.get(0);
				
				for(int i=0; i<dataObject.size(); i++) {
					JSONObject productData = (JSONObject)(dataObject.get(i));
					System.out.println(productData.get("name"));
				}
				
//				System.out.println(productData1.get("name"));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}

}
