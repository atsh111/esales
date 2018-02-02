package com.styros.esales.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.styros.esales.DataMapper;
import com.styros.esales.entity.ProductsEntity;
import com.styros.esales.model.Product;
import com.styros.esales.repository.ProductRepository;
import com.styros.esales.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.security.util.Resources;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 11/19/2017.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;
    public List<Product> getProducts(){
        final List<Product> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        try {

            List<ProductsEntity> productsEntities = repository.findAll();
            productsEntities.forEach(pE -> {
                Product prod = new Product();
                prod.setId(String.valueOf(pE.getId()));
                prod.setName(pE.getName());
                prod.setCode(pE.getCode());
                prod.setDescription(pE.getDescription());
                prod.setPrice(pE.getPrice());
                products.add(prod);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    public Product saveProduct(Product product){
        ProductsEntity prod=null;
        /*prod=repository.findByCode(product.getCode());*/
        if(prod!=null){
            ProductsEntity updatedProduct = DataMapper.getProductEntity(product);
            updatedProduct.setId(prod.getId());
            repository.save(updatedProduct);
        }
        else{
            ProductsEntity newProduct = DataMapper.getProductEntity(product);
            repository.save(newProduct);
        }
        return product;
    }
}
