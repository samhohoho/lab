package com.samhoho.lab.service;

import com.samhoho.lab.model.Cart;
import com.samhoho.lab.model.CartItem;
import com.samhoho.lab.repositories.CartItemRepository;
import com.samhoho.lab.repositories.CartRepository;
import com.samhoho.lab.viewmodel.CartItemVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public void addToCart(List<CartItemVm> cartItemVms) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String customerId = auth.getName();

        Cart cart = cartRepository.findByCustomerIdAndOrderIdIsNull(customerId).stream().findFirst().orElse(null);
        Set<CartItem> existedCartItems = new HashSet<>();
        //TODO: check cartitem here

        if (cart == null) {
            cart = Cart.builder()
                    .customerId(customerId)
                    .cartItems(existedCartItems)
                    .build();
            cart.setCreatedOn(ZonedDateTime.now());
        } else {
            existedCartItems = cartItemRepository.findAllByCart(cart);
        }

        /**
         * Objects are passed by reference id.
         * So changes made in cartItem will reflect on
         * cart.cartItem.
         *
         * After retrieving the existing cartItems, existedCartItems,
         * it attempts to identify cartItemVm in cartItems and increase the quant.
         * Otherwise create a new cartItem.
         */
        for (CartItemVm cartItemVm : cartItemVms) {
            CartItem cartItem = getCartItemByProductId(existedCartItems, cartItemVm.productId());
            if (cartItem.getProductId() != null) {
                cartItem.setQuantity(cartItem.getQuantity() + cartItemVm.quantity());
            } else {
                cartItem.setCart(cart);
                cartItem.setQuantity(cartItemVm.quantity());
                cartItem.setProductId(cartItemVm.productId());
                cartItem.setParentProductId(cartItemVm.parentProductId());
                cart.getCartItems().add(cartItem);
            }
        }
        //TODO: change the quantity without save
        cart = cartRepository.save(cart);
        cartItemRepository.saveAll(cart.getCartItems());
    }

    private CartItem getCartItemByProductId(Set<CartItem> cartItems, Long productId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProductId().equals(productId)) {
                return cartItem;
            }
        }
        return new CartItem();
    }
}
