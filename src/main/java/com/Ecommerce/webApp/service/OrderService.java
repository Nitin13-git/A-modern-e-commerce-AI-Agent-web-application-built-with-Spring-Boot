package com.Ecommerce.webApp.service;

import com.Ecommerce.webApp.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> getOrders();
    Order getOrderById(String id);
    Order updateOrder(String id, Order order);
    void deleteOrder(String id);
} 