package com.styros.esales.model;

import java.util.Date;

/**
 * Created by atul on 11/21/2017.
 */
public class InvoiceSummary {
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String invoiceNo;
    private String date;
}
