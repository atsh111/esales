package com.styros.esales.controller;

import com.styros.esales.model.InvoiceDetails;
import com.styros.esales.model.InvoiceSummary;
import com.styros.esales.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by atul on 11/21/2017.
 */
@RestController
@RequestMapping("/api/invoices/")
public class InvoiceController {
    static String invoicePdfDir="D:\\Projects\\styros\\styros\\pinnacleseven\\Code\\invoicepdf";
    @Autowired
    InvoiceService service;
    @RequestMapping(value = "/{userid}",method = RequestMethod.POST)
    public String createInvoice(@PathVariable int userid,@RequestBody InvoiceDetails invoiceDetails) throws Exception {
        try {
            return service.createInvoice(invoiceDetails,userid);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @RequestMapping(value = "{userid}",method = RequestMethod.GET)
    public List<InvoiceSummary> getInvoiceHistory(@PathVariable int userid){
        return service.getInvoiceHistory(userid);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable(name="id") String id)throws Exception{

        byte[] pdfContent = service.getInvoice(id);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(pdfContent));
        return ResponseEntity.ok()
                .contentLength(pdfContent.length)
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);
    }
}
