package com.styros.esales.repository;

import com.styros.esales.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

/**
 * Created by atul on 11/30/2017.
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer>{

    public UsersEntity findByUsername(String username);
    public UsersEntity findById(int id);
}
