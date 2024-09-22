package com.samhoho.lab.repositories;

import com.samhoho.lab.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findByCustomerId(String customerId);
    List <Cart> findByCustomerIdAndOrderIdIsNull(String customerId);
}
