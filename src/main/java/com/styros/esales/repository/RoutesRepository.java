package com.styros.esales.repository;

import com.styros.esales.entity.RoutesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by atul on 11/30/2017.
 */
@Repository
public interface RoutesRepository extends JpaRepository<RoutesEntity,Integer>{
    public RoutesEntity findByname(String name);
    public RoutesEntity findById(int id);
}
