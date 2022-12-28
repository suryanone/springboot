package com.wide.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wide.dao.RepositoryException;
import com.wide.model.Order;
import com.wide.model.OrderItem;
import com.wide.model.Product;
import com.wide.service.OrderService;
import com.wide.service.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/formOrder", method = RequestMethod.GET)
	public String listOrder(ModelMap map, String new_order, String quantity, HttpServletRequest request) {
		String out = "FormOrder";
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
		
		return out;
	}
	
	@RequestMapping(value = "/formOrder/Order")
	public String listProductOrder(Map<String, Object> map) {
		try {
			map.put("productList", productService.productList());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return "FormAddOrder";
	}
	
	@RequestMapping(value = "/order/save", method = RequestMethod.POST)
	public String saveOrder(@ModelAttribute("order") Order order, BindingResult result, HttpServletRequest request) {
		List<ProductDto> productsDtoSession = (List<ProductDto>) request.getSession().getAttribute("productsDto");
		String out = null;
		if(productsDtoSession != null) {
			for(ProductDto pDto: productsDtoSession) {
				OrderItem orderItem = new OrderItem(pDto.getPrice(), pDto.getQuantity(), pDto.getProductDtoId());
				orderItem.setOrder(order);
				order.addItem(orderItem);
			}
			try {
				orderService.save(order);
				request.getSession().invalidate();
				out = "Save";
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		} else {
			out = "emptyOrder";
		}
		
		//return Save.jsp
		return out;
	}
	
	@RequestMapping("/listOrder")
	public String listProduct(Map<String, Object> map) {
		try {
			map.put("orderList", orderService.orderList());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return "listOrder";
	}
	
	@RequestMapping("/order/{orderId}")
	public String toListOrderItem(@PathVariable("orderId") int orderId, ModelMap map) {
		try {
			map.addAttribute("order", orderService.findOrderById(orderId));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return "listOrderItem";
	}
}
