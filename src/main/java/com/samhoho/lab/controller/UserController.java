package com.samhoho.lab.controller;

import com.samhoho.lab.service.CartService;
import com.samhoho.lab.viewmodel.CartItemVm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final CartService cartService;

    public UserController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/cart")
    public ResponseEntity<List<CartItemVm>> cart(@RequestBody List<CartItemVm> cartItemVms) {
        cartService.addToCart(cartItemVms);
        return new ResponseEntity<>(cartItemVms, HttpStatus.CREATED);
    }
}
