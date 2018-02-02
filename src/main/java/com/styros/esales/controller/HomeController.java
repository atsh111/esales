package com.styros.esales.controller;

import com.styros.esales.DataMapper;
import com.styros.esales.ViewModel.VMSalesLocation;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.model.User;
import com.styros.esales.repository.RoutesRepository;
import com.styros.esales.repository.UsersRepository;
import com.styros.esales.service.SalesLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by atul on 11/30/2017.
 */

@Controller
public class HomeController {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RoutesRepository routesRepository;
    @Autowired
    SalesLocationService salesLocationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(ModelMap map, ServletRequest request, @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        map.put("routes", routesRepository.findAll());
        return "home";
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login(){
        return "index";
    }
    @RequestMapping(value="/login?error",method = RequestMethod.GET)
    public String loginerror(ModelMap map){
        map.put("error","invalid username or password.please try again");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getDefault() {
        return "index";
    }

    @RequestMapping("/home")
    public String home(ModelMap modelMap) {
        List<VMSalesLocation> locations =salesLocationService.get();
        modelMap.put("locations",locations);
        modelMap.put("routes", routesRepository.findAll());
        return "home";
    }

    @RequestMapping("/assign")
    public String assign(ModelMap modelMap) {
        modelMap.put("routes", routesRepository.findAll());
        return "assign";
    }

}