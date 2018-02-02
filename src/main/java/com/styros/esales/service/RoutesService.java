package com.styros.esales.service;

import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.repository.RoutesRepository;
import com.styros.esales.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by atul on 11/26/2017.
 */
@Service
public class RoutesService {

    @Autowired
    RoutesRepository repository;
    public void saveRoute(String routeInfo,String routeName) throws Exception {
        RoutesEntity routesEntity = new RoutesEntity();
        routesEntity.setName(routeName);
        routesEntity.setData(routeInfo);
        repository.save(routesEntity);
    }

    public List<RoutesEntity> getRoutes(){
        return repository.findAll();
    }

    public RoutesEntity findByName(String name){return repository.findByname(name);}

    public RoutesEntity findById(int id){return repository.findById(id);}
}
