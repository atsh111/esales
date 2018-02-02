package com.styros.esales.repository;

import com.styros.esales.entity.SaleslocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by atul on 12/6/2017.
 */
@Repository
public interface SalesLocationRepository extends JpaRepository<SaleslocationEntity,Integer>{
}
