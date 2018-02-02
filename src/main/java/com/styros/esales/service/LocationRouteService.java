package com.styros.esales.service;

import com.styros.esales.entity.LocationRouteMapEntity;
import com.styros.esales.repository.LocationRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by atul on 12/6/2017.
 */
@Service
public class LocationRouteService {
    @Autowired
    LocationRouteRepository locationRouteRepository;

    public void mapRouteToLocation(int routeid,int locationid){
        LocationRouteMapEntity entity = new LocationRouteMapEntity();
        entity.setLocationid(locationid);
        entity.setRouteid(routeid);
        locationRouteRepository.save(entity);
    }

    public List<LocationRouteMapEntity> getByLocationId(int id){
        return locationRouteRepository.findAllByLocationid(id);
    }
}
