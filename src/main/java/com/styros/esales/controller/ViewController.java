package com.styros.esales.controller;

import com.styros.esales.ViewModel.*;
import com.styros.esales.entity.ProductsEntity;
import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.model.Product;
import com.styros.esales.model.User;
import com.styros.esales.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by atul on 12/5/2017.
 */
@Controller
public class ViewController {

    @Autowired
    InvoiceService invoiceService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    RoutesService routesService;
    @Autowired
    SalesLocationService salesLocationService;
    @RequestMapping("/users.html")
    public String getUserView(ModelMap map){
        List<VMUser> users = userService.getVMUsers();
        map.put("users",users);
        List<VMSalesLocation> locations = salesLocationService.get();
        map.put("locations",locations);
        return "users";
    }
    @RequestMapping("/invoices.html")
    public String getInvoiceView(ModelMap map){
        List<VMInvoice> invoices = invoiceService.getVMInvoices();
        map.put("invoices",invoices);
        return "invoices";
    }

    @RequestMapping("/routes.html")
    public String getRoutes(ModelMap map){
        List<RoutesEntity> routes = routesService.getRoutes();
        map.put("routes",routes);
        return "routes";
    }

    @RequestMapping("/products.html")
    public String getProducts(ModelMap map){
        List<Product> products = productService.getProducts();
        map.put("products",products);
        return "products";
    }

    @RequestMapping("/locations.html")
    public String getLocations(ModelMap map){
        List<VMSalesLocation> lst = salesLocationService.get();
        map.put("locations",lst);
        return "locations";
    }
}
