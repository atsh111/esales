package com.styros.esales.repository;

import com.styros.esales.entity.LocationRouteMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by atul on 12/6/2017.
 */
@Repository
public interface LocationRouteRepository extends JpaRepository<LocationRouteMapEntity,Integer>{
    public List<LocationRouteMapEntity> findAllByRouteid(int id);
    public List<LocationRouteMapEntity> findAllByLocationid(int id);
}