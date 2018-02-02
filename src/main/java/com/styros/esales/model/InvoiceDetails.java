package com.styros.esales.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by atul on 11/21/2017.
 */
public class InvoiceDetails {

    public InvoiceDetails(){
        productCartItems = new ArrayList<>();
    }
    public List<ProductCartItem> getProductCartItems() {
        return productCartItems;
    }

    public void setProductCartItems(List<ProductCartItem> productCartItems) {
        this.productCartItems = productCartItems;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    private List<ProductCartItem> productCartItems;
    private CustomerDetails customerDetails;

    public int getSalespersonid() {
        return salespersonid;
    }

    public void setSalespersonid(int salespersonid) {
        this.salespersonid = salespersonid;
    }

    private int salespersonid;
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    private String locationInfo;
    private String invoiceId;

}
