package com.styros.esales.controller;

import com.styros.esales.entity.InvoicesEntity;
import com.styros.esales.model.Product;
import com.styros.esales.model.User;
import com.styros.esales.service.InvoiceService;
import com.styros.esales.service.ProductService;
import com.styros.esales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by atul on 12/4/2017.
 */
@RestController
public class WebRestController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;


    @RequestMapping("/admin/invoices")
    public List<InvoicesEntity> getInvoices(){
        return invoiceService.findAll();
    }

    @RequestMapping("/admin/products")
    public List<Product> findAllProducts(){
        return productService.getProducts();
    }

    @RequestMapping("/admin/users")
    public List<User> findAllUsers(){
        return userService.getUsers();
    }


}
