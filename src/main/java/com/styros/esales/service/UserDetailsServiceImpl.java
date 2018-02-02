package com.styros.esales.service;

import com.styros.esales.entity.UsersEntity;
import com.styros.esales.model.UserDetailsImpl;
import com.styros.esales.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by atul on 12/1/2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsersRepository repository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UsersEntity user=repository.findByUsername(s);
        if(user == null)
            throw new UsernameNotFoundException("username or password is incorrect.");

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        return userDetails;
    }

}
