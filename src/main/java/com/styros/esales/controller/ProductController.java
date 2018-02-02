package com.styros.esales.controller;

import com.styros.esales.model.Product;
import com.styros.esales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by atul on 11/19/2017.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    public List<Product> getProducts(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.getProducts();
    }

}
