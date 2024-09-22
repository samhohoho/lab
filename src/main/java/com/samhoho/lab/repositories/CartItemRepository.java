package com.samhoho.lab.repositories;

import com.samhoho.lab.model.Cart;
import com.samhoho.lab.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Set<CartItem> findAllByCart(Cart cart);

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
}
