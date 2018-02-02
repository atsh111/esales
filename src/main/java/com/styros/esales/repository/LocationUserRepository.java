package com.styros.esales.repository;

import com.styros.esales.entity.LocationUserMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by atul on 12/6/2017.
 */
public interface LocationUserRepository extends JpaRepository<LocationUserMapEntity,Integer> {
    public LocationUserMapEntity findFirstByUserid(int userid);
}
