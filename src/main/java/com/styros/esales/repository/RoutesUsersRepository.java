package com.styros.esales.repository;

import com.styros.esales.entity.RouteUserMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by atul on 11/30/2017.
 */
@Repository
public interface RoutesUsersRepository extends JpaRepository<RouteUserMappingEntity,Integer>{

    public List<RouteUserMappingEntity> findAllByRouteId(int routeid);
    public List<RouteUserMappingEntity> findAllByUserId(int userid);
}
