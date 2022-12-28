package com.wide.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.wide.model.Order;
import com.wide.model.OrderItem;
import com.wide.model.Product;

@Controller
public class OrderController {
	
	private static final String GET_PRODUCTS_ENDPOINT_URL = "http://localhost:8989/products";
	private static final String GET_PRODUCT_ENDPOINT_URL = "http://localhost:8989/products/{productId}";
    private static final String CREATE_ORDER_ENDPOINT_URL = "http://localhost:8989/orders";
    private static final String GET_ORDERS_ENDPOINT_URL = "http://localhost:8989/orders";
    private static final String GET_ORDER_ENDPOINT_URL = "http://localhost:8989/orders/{orderId}";
    private static final String DELETE_ORDER_ENDPOINT_URL = "http://localhost:8989/orders/{orderId}";
    private static final String UPDATE_ORDER_ENDPOINT_URL = "http://localhost:8989/orders/{orderId}";
    private static RestTemplate restTemplate = new RestTemplate();
    
    @GetMapping("/formOrder")
    public String formOrder(String new_order, String quantity, Model map, HttpServletRequest request) {
    	String out = "FormOrder";
    	if(request.getSession().getAttribute("productsDto") != null) {
    		List<ProductDto> productsDtoSession = (List<ProductDto>) request.getSession().getAttribute("productsDto");
    		double totalOrderSession = (Double) request.getSession().getAttribute("totalOrder");
    		double totalOrder = 0;
    		if(new_order!=null) {
    			if(quantity!=null && quantity!="" && Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) <=100) {
    				String[] dataSplit = new_order.split("-");
    				int productId = Integer.parseInt(dataSplit[0].trim());
    				String code = dataSplit[1].trim();
    				String name = dataSplit[2].trim();
    				String type = dataSplit[3].trim();
    				double price = Double.parseDouble(dataSplit[4].trim());
    				int quantityFix = Integer.parseInt(quantity);
    				double totalPrice = price*quantityFix;
    				ProductDto productDto = new ProductDto(productId, name, price, quantityFix, totalPrice);
    				
    					int count = 0;
    					for(ProductDto pDto: productsDtoSession) {
    						if (pDto.getProductDtoId() == productDto.getProductDtoId()) {
    							pDto.setQuantity(pDto.getQuantity() + quantityFix);
    							double newTotalPrice = pDto.getQuantity() * pDto.getPrice();
    							pDto.setTotalPrice(newTotalPrice);
    							count = 1;
    						}
    						totalOrder += pDto.getTotalPrice();
    					}
    					if(count == 0) {
    						productsDtoSession.add(productDto);
    						double sessionTotalOrder = 0;
    						for(ProductDto pDto: productsDtoSession) {
    							sessionTotalOrder += pDto.getTotalPrice();
    						}
    						totalOrder = sessionTotalOrder;
    					}
    			} else {
    				out = "qtyProductError";
    			}
    			totalOrderSession = totalOrder;
    			request.getSession().setAttribute("totalOrder", totalOrderSession);
        	} 
    	} else {
    		if(new_order!=null) {
    			if(quantity!=null && quantity!="" && Integer.parseInt(quantity) > 0 && Integer.parseInt(quantity) <=100) {
    				String[] dataSplit = new_order.split("-");
    				int productId = Integer.parseInt(dataSplit[0].trim());
    				String code = dataSplit[1].trim();
    				String name = dataSplit[2].trim();
    				String type = dataSplit[3].trim();
    				double price = Double.parseDouble(dataSplit[4].trim());
    				int quantityFix = Integer.parseInt(quantity);
    				double totalPrice = price*quantityFix;
    				ProductDto productDto = new ProductDto(productId, name, price, quantityFix, totalPrice);
    				double totalOrder = 0;
    				if(request.getSession().getAttribute("productsDto") != null) {
    					List<ProductDto> productsDtoSession = (List<ProductDto>) request.getSession().getAttribute("productsDto");
    					int count = 0;
    					for(ProductDto pDto: productsDtoSession) {
    						if (pDto.getProductDtoId() == productDto.getProductDtoId()) {
    							pDto.setQuantity(pDto.getQuantity() + quantityFix);
    							double newTotalPrice = pDto.getQuantity() * pDto.getPrice();
    							pDto.setTotalPrice(newTotalPrice);
    							count = 1;
    						}
    						totalOrder += pDto.getTotalPrice();
    					}
    					if(count == 0) {
    						productsDtoSession.add(productDto);
    						double sessionTotalOrder = 0;
    						for(ProductDto pDto: productsDtoSession) {
    							sessionTotalOrder += pDto.getTotalPrice();
    						}
    						totalOrder = sessionTotalOrder;
    					}
    				} else {
    					List<ProductDto> productsDto = new ArrayList<ProductDto>();
    					productsDto.add(productDto);
    					request.getSession().setAttribute("productsDto", productsDto);
    					totalOrder = totalPrice;
    				}
    				request.getSession().setAttribute("totalOrder", totalOrder);
    			} else {
    				out = "qtyProductError";
    			}
        	} 
    	}
    	
    	return out;
    }
    
    @GetMapping("/formAddOrder")
    public String toAddOrder(Model map) {
    	ResponseEntity<List<Product>> response = restTemplate.exchange(GET_PRODUCTS_ENDPOINT_URL, HttpMethod.GET, null,  new ParameterizedTypeReference<List<Product>>() {});
    	map.addAttribute("productList", response.getBody());
    	return "FormAddOrder";
    }
    
    @PostMapping(value = "/order/save")
    public String saveOrder(@ModelAttribute("order") Order order, BindingResult result, HttpServletRequest request) {
    	List<ProductDto> productsDtoSession = (List<ProductDto>) request.getSession().getAttribute("productsDto");
		String out = null;
		String orderId = (String) request.getSession().getAttribute("orderId");
		if(productsDtoSession != null) {
			
//			orderService.save(order);
			if(orderId != null) {
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				
				Map< String, String > params = new HashMap< String, String > ();
				params.put("orderId", orderId);
				for(ProductDto pDto: productsDtoSession) {
					OrderItem orderItem = new OrderItem(pDto.getPrice(), pDto.getQuantity(), pDto.getProductDtoId());
					orderItem.setOrder(order);
					orderItems.add(orderItem);
				}
				order.setOrderItems(orderItems);
				restTemplate.put(UPDATE_ORDER_ENDPOINT_URL, order.getOrderItems(), params);
				request.getSession().invalidate();
			} else {
				for(ProductDto pDto: productsDtoSession) {
					OrderItem orderItem = new OrderItem(pDto.getPrice(), pDto.getQuantity(), pDto.getProductDtoId());
					orderItem.setOrder(order);
					order.addItem(orderItem);
				}
				restTemplate.postForEntity(CREATE_ORDER_ENDPOINT_URL, order.getOrderItems(), Order.class);
				request.getSession().invalidate();
			}
			out = "Save";
		} else {
			out = "emptyOrder";
		}
		
		//return Save.jsp
		return out;
    }
    
    @GetMapping("/listOrder")
	public String listOrder(Model map) {
		ResponseEntity<List<Order>> response = restTemplate.exchange(GET_ORDERS_ENDPOINT_URL, HttpMethod.GET, null,  new ParameterizedTypeReference<List<Order>>() {});
		map.addAttribute("orderList", response.getBody());
		return "listOrder";
	}
    
    @GetMapping("/deleteOrder/{orderId}")
	public String deleteOrder(@PathVariable("orderId") String orderId) {
    	Map<String, String> params = new HashMap<String ,String>();
    	params.put("orderId", orderId);
    	restTemplate.delete(DELETE_ORDER_ENDPOINT_URL, params);
		return "redirect:/listOrder";
	}
    
    @GetMapping("/updateOrder/{orderId}")
    public String updateOrder(@PathVariable("orderId") String orderId, HttpServletRequest request) {
    	Map< String, String > params = new HashMap< String, String > ();
		params.put("orderId", orderId);
		ResponseEntity<Order> response = restTemplate.getForEntity(GET_ORDER_ENDPOINT_URL, Order.class, params);
		request.getSession().setAttribute("orderBody", response.getBody());
		request.getSession().setAttribute("orderId", orderId);
		
		List<ProductDto> productsDto = new ArrayList<ProductDto>();
		double totalOrder = 0;
		for(OrderItem orderItem: response.getBody().getOrderItems()) {
			Map< String, Integer > params2 = new HashMap< String, Integer > ();
			params2.put("productId", orderItem.getProductIdFk());
			ResponseEntity<Product> response2 =  restTemplate.getForEntity(GET_PRODUCT_ENDPOINT_URL, Product.class, params2);
			String name = response2.getBody().getName();
			
			double totalPrice = orderItem.getPrice() * orderItem.getQuantity();
			ProductDto productDto = new ProductDto(orderItem.getProductIdFk(), name, orderItem.getPrice(), orderItem.getQuantity(), totalPrice);
			productsDto.add(productDto);
			totalOrder += totalPrice;
		}
		request.getSession().setAttribute("productsDto", productsDto);
		request.getSession().setAttribute("totalOrder", totalOrder);
    	return "redirect:/formOrder";
    }
	
	@GetMapping("/order/{orderId}")
	public String toListOrderItem(@PathVariable("orderId") String orderId, Model map) {
		Map< String, String > params = new HashMap< String, String > ();
		params.put("orderId", orderId);
		ResponseEntity<Order> response = restTemplate.getForEntity(GET_ORDER_ENDPOINT_URL, Order.class, params);
		map.addAttribute("order", response.getBody());
		return "listOrderItem";
	}
}
