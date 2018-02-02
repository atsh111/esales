package com.styros.esales.service;

import com.styros.esales.entity.RouteUserMappingEntity;
import com.styros.esales.model.RouteUserMap;
import com.styros.esales.repository.RoutesUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 12/2/2017.
 */
@Service
public class RouteUserMapService {
    @Autowired
    RoutesUsersRepository repository;
    public List<Integer> getUsersAssignedToRoute(int routeId){
        List<Integer> users = new ArrayList<>();
        repository.findAll()
                .forEach(routeUserMappingEntity -> {
                    if(routeUserMappingEntity.getRouteId().equals(routeId))
                        users.add(routeUserMappingEntity.getUserId());
                });
        return users;
    }

    public List<RouteUserMappingEntity> getAll(int routeId){
        return repository.findAllByRouteId(routeId);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public void save(RouteUserMappingEntity saveObj){
        repository.save(saveObj);
    }

    public List<RouteUserMappingEntity> getAllForUser(int userid){
        return repository.findAllByUserId(userid);
    }
}
