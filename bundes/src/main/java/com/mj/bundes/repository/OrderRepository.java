package com.mj.bundes.repository;

import org.springframework.data.repository.CrudRepository;

import com.mj.bundes.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
