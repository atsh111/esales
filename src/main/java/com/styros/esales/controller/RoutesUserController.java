package com.styros.esales.controller;

import com.styros.esales.entity.LocationRouteMapEntity;
import com.styros.esales.entity.RouteUserMappingEntity;
import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.model.RouteDetails;
import com.styros.esales.model.RouteDetailsCreateEntity;
import com.styros.esales.model.RouteUserMap;
import com.styros.esales.model.User;
import com.styros.esales.repository.RoutesUsersRepository;
import com.styros.esales.service.LocationRouteService;
import com.styros.esales.service.RouteUserMapService;
import com.styros.esales.service.RoutesService;
import com.styros.esales.service.UserService;
import com.styros.esales.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atul on 12/2/2017.
 */
@RestController
@RequestMapping("/api/routeuser/")
public class RoutesUserController {
    @Autowired
    RoutesUsersRepository repository;
    @Autowired
    RoutesService service;
    @Autowired
    UserService userService;
    @Autowired
    LocationRouteService locationRouteService;
    @Autowired
    RouteUserMapService userMapService;
    @RequestMapping(value = "/maprouteuser/",method = RequestMethod.POST)
    public void mapUserToRoutes(@RequestBody String routeUserMap){
        RouteUserMap[] map= JsonHelper.convertToType(routeUserMap, RouteUserMap[].class);
        List<RouteUserMappingEntity> mappingEntityList = new ArrayList<>();
        Arrays.asList(map)
                .forEach(item -> {
                    RouteUserMappingEntity me = new RouteUserMappingEntity();
                    me.setRouteId(item.getRouteId());
                    me.setUserId(item.getUserid());
                    mappingEntityList.add(me);
                    repository.save(me);
                });


    }

    @RequestMapping(value = "getbyroutename/{routeName}",method = RequestMethod.GET)
    public RouteDetails getRouteDetails(@PathVariable(name = "routeName")String routeName){
        RoutesEntity route=service.findByName(routeName);
        List<Integer> uids=userMapService.getUsersAssignedToRoute(route.getId());
        RouteDetails routeDetails = new RouteDetails();
        List<User> allUsers = userService.getUsers();
        List<User> users = new ArrayList<>();
        List<User> unAssUsers = new ArrayList<>();
        routeDetails.setRouteId(route.getId());
        allUsers.forEach(u->{

            if(uids.contains(u.getId()))
                users.add(u);
            else
                unAssUsers.add(u);
        });
        routeDetails.setUnassignedUsers(unAssUsers);
        routeDetails.setUsers(users);
        return routeDetails;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String saveRouteDetails(@RequestBody RouteDetailsCreateEntity routeDetails){
        List<RouteUserMappingEntity> currentMapping=userMapService.getAll(routeDetails.getRouteId());
        currentMapping.forEach(current-> userMapService.delete(current.getId()));
        List<RouteUserMappingEntity> newMapping = new ArrayList<>();
        routeDetails.getUsers()
                    .forEach(user->{
                            RouteUserMappingEntity obj = new RouteUserMappingEntity();
                            obj.setRouteId(routeDetails.getRouteId());
                            obj.setUserId(user);
                            userMapService.save(obj);
                    });
        return "{\"result\":\"success\"}";
    }

    @RequestMapping(value = "getbyuserid/{userid}",method = RequestMethod.GET)
    public List<RoutesEntity> getRoutesForUser(@PathVariable(name="userid")String user){
        Integer userid = Integer.parseInt(user);
        List<RoutesEntity> routeList = new ArrayList<>();
        User userEntity= userService.getByUserId(userid);
        if(userEntity == null || userEntity.getLocationId() == 0)
            return routeList;
        List<LocationRouteMapEntity> locRouteMap=locationRouteService.getByLocationId(userEntity.getLocationId());
        locRouteMap.forEach(x->{
            RoutesEntity entity=service.findById(x.getRouteid());
            routeList.add(entity);
                }
        );

        return routeList;
    }
}
