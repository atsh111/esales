package com.styros.esales.controller;

import com.styros.esales.entity.RouteUserMappingEntity;
import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.model.RouteDetails;
import com.styros.esales.model.User;
import com.styros.esales.service.LocationRouteService;
import com.styros.esales.service.RouteUserMapService;
import com.styros.esales.service.RoutesService;
import com.styros.esales.service.UserService;
import com.styros.esales.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 11/26/2017.
 */
@RestController
@RequestMapping("/api/route/")
@CrossOrigin
public class RoutesController {

    @Autowired
    RoutesService service;
    @Autowired
    UserService userService;
    @Autowired
    RouteUserMapService userMapService;

    @Autowired
    LocationRouteService locationRouteService;
    @PostMapping("/{name}/{locid}")
    public String postRoute(@RequestBody String routeInfo,@PathVariable(name = "name") String routeName,
                            @PathVariable(name = "locid") int locid) throws Exception {
        service.saveRoute(routeInfo,routeName);
        RoutesEntity routesEntity= service.findByName(routeName);
        locationRouteService.mapRouteToLocation(routesEntity.getId(),locid);
        return "{\"result\":\"success\"}";
    }

    @GetMapping
    public List<RoutesEntity> getRoutes(){
        return service.getRoutes();
    }


}
