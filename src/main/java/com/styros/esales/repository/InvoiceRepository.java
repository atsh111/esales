package com.styros.esales.repository;

import com.styros.esales.entity.InvoicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by atul on 11/30/2017.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<InvoicesEntity,Integer> {
}
