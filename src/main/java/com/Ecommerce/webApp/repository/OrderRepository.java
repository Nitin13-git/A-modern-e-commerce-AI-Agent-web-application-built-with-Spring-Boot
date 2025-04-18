package com.Ecommerce.webApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Ecommerce.webApp.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
