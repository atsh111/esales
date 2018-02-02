package com.styros.esales.repository;

import com.styros.esales.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by atul on 11/30/2017.
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity,Integer>{
    //public ProductsEntity findByCode(String code);
}
