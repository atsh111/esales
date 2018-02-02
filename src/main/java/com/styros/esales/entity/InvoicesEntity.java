package com.styros.esales.entity;

import javax.persistence.*;

/**
 * Created by atul on 11/30/2017.
 */
@Entity
@Table(name = "invoices", schema = "esales", catalog = "esales")
public class InvoicesEntity {
    private int id;
    private String date;
    private String customeraddress;
    private Integer salesmanid;
    private String details;
    private String salelocation;
    private String invoicenum;
    private String data;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = true, length = -1)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "customeraddress", nullable = true, length = -1)
    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    @Basic
    @Column(name = "salesmanid", nullable = true)
    public Integer getSalesmanid() {
        return salesmanid;
    }

    public void setSalesmanid(Integer salesmanid) {
        this.salesmanid = salesmanid;
    }

    @Basic
    @Column(name = "details", nullable = true, length = -1)
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "salelocation", nullable = true, length = -1)
    public String getSalelocation() {
        return salelocation;
    }

    public void setSalelocation(String salelocation) {
        this.salelocation = salelocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvoicesEntity that = (InvoicesEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (customeraddress != null ? !customeraddress.equals(that.customeraddress) : that.customeraddress != null)
            return false;
        if (salesmanid != null ? !salesmanid.equals(that.salesmanid) : that.salesmanid != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (salelocation != null ? !salelocation.equals(that.salelocation) : that.salelocation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (customeraddress != null ? customeraddress.hashCode() : 0);
        result = 31 * result + (salesmanid != null ? salesmanid.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (salelocation != null ? salelocation.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "invoicenum", nullable = true, length = -1)
    public String getInvoicenum() {
        return invoicenum;
    }

    public void setInvoicenum(String invoicenum) {
        this.invoicenum = invoicenum;
    }

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
