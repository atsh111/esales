package com.styros.esales.controller;

import com.styros.esales.ViewModel.VMSalesLocation;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.service.SalesLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by atul on 12/6/2017.
 */
@RestController
@RequestMapping("/api/locations/")
public class LocationController {

    @Autowired
    SalesLocationService service;
    @PostMapping
    public String save(@RequestBody SaleslocationEntity saleslocationEntity){
        service.save(saleslocationEntity);
        return "{\"result\":\"success\"}";
    }

    @GetMapping
    public List<VMSalesLocation> salesLocations(){
        return service.get();
    }
}
