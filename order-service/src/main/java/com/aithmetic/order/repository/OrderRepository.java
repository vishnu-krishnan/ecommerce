package com.aithmetic.order.repository;

import com.aithmetic.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long>{

}
