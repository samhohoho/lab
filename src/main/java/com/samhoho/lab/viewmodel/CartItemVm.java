package com.samhoho.lab.viewmodel;

import com.samhoho.lab.model.CartItem;

public record CartItemVm(Long productId, int quantity, Long parentProductId) {
    public static CartItemVm fromModel(CartItem cartItem) {
        return new CartItemVm(cartItem.getProductId(), cartItem.getQuantity(), cartItem.getParentProductId());
    }
}