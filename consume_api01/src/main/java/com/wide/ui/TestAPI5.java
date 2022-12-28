package com.wide.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestAPI5 {
	//override not support
	public static void main(String[] args) {
		// API:
		// http://localhost:8989/orders
		// http://localhost:8989/products
		try {
//			allowMethods("PATCH");
			URL url = new URL("http://localhost:8989/products/8");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PATCH");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonBody = "{\"price\":2000}";
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
		catch (Exception e1) {
			e1.printStackTrace();
		}

	}
//	
//	public static void allowMethods(String... methods) throws Exception {
//        try {
//            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
//            Field modifiersField = Field.class.getDeclaredField("modifiers");
//            modifiersField.setAccessible(true);
//            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);
//
//            methodsField.setAccessible(true);
//
//            String[] oldMethods = (String[]) methodsField.get(null);
//            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
//            methodsSet.addAll(Arrays.asList(methods));
//            String[] newMethods = methodsSet.toArray(new String[0]);
//
//            methodsField.set(null/*static field*/, newMethods);
//        } catch (NoSuchFieldException | IllegalAccessException e) {
//            throw new IllegalStateException(e);
//        }
//    }

}
