package com.styros.esales.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.styros.esales.DataMapper;
import com.styros.esales.ViewModel.VMUser;
import com.styros.esales.entity.RouteUserMappingEntity;
import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.entity.UsersEntity;
import com.styros.esales.model.Product;
import com.styros.esales.model.RouteUserMap;
import com.styros.esales.model.User;
import com.styros.esales.repository.UsersRepository;
import com.styros.esales.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.misc.VM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 11/19/2017.
 */
@Service
public class UserService {
    @Autowired
    UsersRepository repository;
    @Autowired
    SalesLocationService salesLocationService;
    @Autowired
    RoutesService routesService;
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
            repository.findAll().forEach(ue-> users.add(DataMapper.getUser(ue)));
            return users;
    }


    public List<VMUser> getVMUsers(){
        List<VMUser> vmUsers = new ArrayList<>();
        List<User> users=getUsers();

        users.forEach(user->{

            VMUser vmUser = new VMUser();
            vmUser.setUserid(user.getId());
            vmUser.setAdmin(user.isAdmin());
            vmUser.setFullname(user.getFullName());
            vmUser.setUsername(user.getUsername());
            SaleslocationEntity saleslocationEntity= salesLocationService.getLocationById(user.getLocationId());
            if(saleslocationEntity != null)
                vmUser.setLocation(saleslocationEntity.getName());
            vmUsers.add(vmUser);
        });

        return vmUsers;
    }

    public void createUser(User user){
        UsersEntity usersEntity = DataMapper.getUserEntity(user);
        repository.save(usersEntity);
    }

    public User getByUserId(int id){
        try {
            return DataMapper.getUser(repository.findById(id));
        }
        catch (Exception ex){
            return null;
        }
    }

}
