package com.styros.esales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by atul on 12/1/2017.
 */
@Service
public class DAOAuthenticationProviderImpl extends DaoAuthenticationProvider {

    public DAOAuthenticationProviderImpl(){
        setUserDetailsService(new UserDetailsServiceImpl());
    }

}
