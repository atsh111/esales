package com.styros.esales;

import com.styros.esales.ViewModel.VMSalesLocation;
import com.styros.esales.entity.InvoicesEntity;
import com.styros.esales.entity.ProductsEntity;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.entity.UsersEntity;
import com.styros.esales.model.InvoiceDetails;
import com.styros.esales.model.Product;
import com.styros.esales.model.User;
import com.styros.esales.utils.JsonHelper;

/**
 * Created by atul on 11/30/2017.
 */
public class DataMapper {

    public static Product getProduct(ProductsEntity pe){
        Product product = new Product();
        product.setId(String.valueOf(pe.getId()));
        product.setPrice(pe.getPrice());
        product.setName(pe.getName());
        product.setDescription(pe.getDescription());
        return product;
    }

    public static ProductsEntity getProductEntity(Product product){
        ProductsEntity pe = new ProductsEntity();
        pe.setCode(product.getCode());
        pe.setId(Integer.parseInt(product.getId()));
        pe.setName(product.getName());
        pe.setDescription(product.getDescription());
        pe.setPrice(product.getPrice());
        return pe;
    }

    public static User getUser(UsersEntity ue){
        User user = new User();
        user.setFullName(ue.getFullname());
        user.setUserId(ue.getUserid());
        user.setUsername(ue.getUsername());
        user.setId(ue.getId());
        user.setPassword(ue.getPwd());
        user.setAdmin(ue.getAdmin());
        user.setLocationId(ue.getLocationid());
        return user;
    }

    public static UsersEntity getUserEntity(User ue){
        UsersEntity user = new UsersEntity();
        user.setFullname(ue.getFullName());
        user.setUserid(ue.getUserId());
        user.setUsername(ue.getUsername());
        user.setPwd(ue.getPassword());
        user.setAdmin(ue.isAdmin());
        user.setLocationid(ue.getLocationId());
        return user;
    }
    public static InvoicesEntity getInvoiceEntity(InvoiceDetails invoiceDetails){
        InvoicesEntity entity = new InvoicesEntity();
        entity.setInvoicenum(invoiceDetails.getInvoiceId());
        entity.setCustomeraddress(invoiceDetails.getCustomerDetails().getFullAddress());
        entity.setDate(String.valueOf(invoiceDetails.getDate()));
        String jsonOutput= JsonHelper.convertToJson(invoiceDetails);
        entity.setSalelocation(invoiceDetails.getLocationInfo());
        entity.setSalesmanid(invoiceDetails.getSalespersonid());
        entity.setData(jsonOutput);
        return entity;
    }

    public static VMSalesLocation getSalesLoc(SaleslocationEntity saleslocationEntity){
        VMSalesLocation entity = new VMSalesLocation();
        entity.setId(saleslocationEntity.getId());
        entity.setGstNo(saleslocationEntity.getGstno());
        entity.setAddress(saleslocationEntity.getAddress());
        entity.setName(saleslocationEntity.getName());
        return entity;
    }
}
