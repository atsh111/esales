package com.styros.esales.service;

import com.styros.esales.DataMapper;
import com.styros.esales.ViewModel.VMSalesLocation;
import com.styros.esales.entity.LocationRouteMapEntity;
import com.styros.esales.entity.LocationUserMapEntity;
import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.repository.LocationRouteRepository;
import com.styros.esales.repository.LocationUserRepository;
import com.styros.esales.repository.RoutesRepository;
import com.styros.esales.repository.SalesLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 12/6/2017.
 */
@Service
public class SalesLocationService {
    @Autowired
    SalesLocationRepository salesLocationRepository;
    @Autowired
    LocationRouteRepository locationRouteRepository;
    @Autowired
    RoutesRepository routesRepository;
    @Autowired
    LocationUserRepository locationUserRepository;
    public void save(SaleslocationEntity entity){
        salesLocationRepository.save(entity);
    }


    public List<VMSalesLocation> get(){
        List<SaleslocationEntity> sles = salesLocationRepository.findAll();
        List<VMSalesLocation> vm = new ArrayList<>();
        List<LocationRouteMapEntity> lrmes=locationRouteRepository.findAll();

        sles.forEach(sle->{
            VMSalesLocation item = DataMapper.getSalesLoc(sle);

            String routes="";

            for(int i=0;i<lrmes.size();i++){
                if(lrmes.get(i).getLocationid() == sle.getId()){
                    RoutesEntity routesEntity=routesRepository.findById(lrmes.get(i).getRouteid());
                    routes=routes+","+ routesEntity.getName();
                }
            }
            item.setRoutes(routes);
            vm.add(item);
        });

        return vm;
    }


    public SaleslocationEntity getLocationById(int id){
        List<SaleslocationEntity> saleslocationEntity = salesLocationRepository.findAll();
        SaleslocationEntity location= saleslocationEntity
                .stream()
                .filter((x-> x.getId() == id))
                .findAny()
                .orElse(null);
        return location;
    }
}
