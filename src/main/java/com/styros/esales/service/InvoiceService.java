package com.styros.esales.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.styros.esales.DataMapper;
import com.styros.esales.ViewModel.VMInvoice;
import com.styros.esales.entity.InvoicesEntity;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.entity.UsersEntity;
import com.styros.esales.model.InvoiceDetails;
import com.styros.esales.model.InvoiceSummary;
import com.styros.esales.repository.InvoiceRepository;
import com.styros.esales.repository.SalesLocationRepository;
import com.styros.esales.repository.UsersRepository;
import com.styros.esales.utils.FileUtils;
import com.styros.esales.utils.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by atul on 11/21/2017.
 */
@Service
public class InvoiceService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SalesLocationRepository salesLocationRepository;
    @Autowired
    InvoiceRepository repository;
    @Value("${invoicedir}")
    String invoicedir;
    private byte[] generatePdf(InvoiceDetails invoiceDetails,SaleslocationEntity saleslocationEntity)throws Exception{
        byte[] pdfContents=null;
        InvoicePrintUtil printUtil = new InvoicePrintUtil(invoiceDetails,saleslocationEntity);
        pdfContents=printUtil.createPdf(invoicedir+"//"+invoiceDetails.getInvoiceId()+".pdf");
        return pdfContents;
    }

    public byte[] getInvoice(String invoicenum)throws Exception{
        String fullPath = invoicedir +"//"+invoicenum+".pdf";
        return Files.readAllBytes(Paths.get(fullPath));
    }
    public String generateInvoiceNo(){
        String invNo =String.format("INV%s",UUID.randomUUID().toString().substring(0,5));
        //String invNo = "INV010101";
        return invNo;
    }

    public String createInvoice(InvoiceDetails invoiceDetails,int userid) throws Exception {

        UsersEntity usersEntity= usersRepository.findById(userid);
        SaleslocationEntity parentLocation = salesLocationRepository.findOne(usersEntity.getLocationid());
        invoiceDetails.setDate(new Date());
        if(invoiceDetails.getInvoiceId()==null)
            invoiceDetails.setInvoiceId(generateInvoiceNo());
        String invJsonPath = invoicedir+"//"+invoiceDetails.getInvoiceId();
        String jsonOutput= JsonHelper.convertToJson(invoiceDetails);
        FileUtils.writeStringToFile(invJsonPath,jsonOutput);
        String locationInfo =invoiceDetails.getLocationInfo();
        if(locationInfo==null)
            locationInfo="("+parentLocation.getName()+")";
        else
            locationInfo+=locationInfo="("+parentLocation.getName()+")";
        invoiceDetails.setLocationInfo(locationInfo);
        generatePdf(invoiceDetails,parentLocation);
        InvoicesEntity invoicesEntity = DataMapper.getInvoiceEntity(invoiceDetails);
        repository.save(invoicesEntity);
        return invoiceDetails.getInvoiceId();
    }

    public List<InvoiceSummary> getInvoiceHistory(int userid){
            List<InvoiceSummary> summaries = new ArrayList<>();
            List<InvoicesEntity> invoicesEntities = repository.findAll();
            invoicesEntities.sort((x,y)->{
                return y.getId()-x.getId();
            });
            invoicesEntities.forEach(x-> {
                if(x.getSalesmanid()==userid) {
                    InvoiceSummary summary = new InvoiceSummary();
                    summary.setInvoiceNo(x.getInvoicenum());
                    summary.setDate(x.getDate());
                    summaries.add(summary);
                }
            });
            return summaries;
    }

    public List<InvoicesEntity> findAll(){
        return repository.findAll();
    }

    public List<VMInvoice> getVMInvoices(){
        List<VMInvoice> vmInvoices = new ArrayList<>();
        List<InvoicesEntity> invoices = findAll();


        invoices.forEach(inv->{

            VMInvoice vmInvoice = new VMInvoice();
            vmInvoice.setId(inv.getId());
            vmInvoice.setNum(inv.getInvoicenum());
            vmInvoice.setCreatedon(inv.getDate());
            vmInvoice.setLocation(inv.getSalelocation());
            int salesmanid=inv.getSalesmanid();
            UsersEntity salesmanUser = usersRepository.findOne(salesmanid);
            vmInvoice.setCustomerDetails(inv.getCustomeraddress());
            vmInvoice.setCreatedby(salesmanUser.getUsername());
            vmInvoices.add(vmInvoice);
        });
        return vmInvoices;
    }
}
