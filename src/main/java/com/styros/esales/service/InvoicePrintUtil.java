package com.styros.esales.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.styros.esales.entity.SaleslocationEntity;
import com.styros.esales.model.InvoiceDetails;
import com.styros.esales.model.ProductCartItem;
import com.styros.esales.repository.SalesLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by atul on 11/21/2017.
 */
public class InvoicePrintUtil {

    @Autowired
    static Font medfont = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL, BaseColor.BLACK);
    static Font largefont = new Font(Font.FontFamily.HELVETICA,20,Font.NORMAL,BaseColor.LIGHT_GRAY);
    SaleslocationEntity saleslocationEntity;
    InvoiceDetails inputData;
    public InvoicePrintUtil(InvoiceDetails invoiceDetails, SaleslocationEntity saleslocationEntity){
        inputData = invoiceDetails;
        this.saleslocationEntity = saleslocationEntity;
    }

    public byte[] createPdf(String pathToPdf)throws Exception{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathToPdf));
        document.open();

        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Phrase("Billed By:",medfont));
        cell1.disableBorderSide(Rectangle.RIGHT);
        table.addCell(cell1);
        Phrase address = buildAddressPhrase(splitbycomma(saleslocationEntity.getAddress()));
        PdfPCell cell2 = new PdfPCell(address);
        cell2.setFixedHeight(60);
        cell2.disableBorderSide(Rectangle.LEFT);
        cell2.disableBorderSide(Rectangle.RIGHT);
        cell2.setColspan(2);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell2);
        PdfPCell cell3 = new PdfPCell();
        cell3.disableBorderSide(Rectangle.LEFT);
        table.addCell(cell3);
        document.add(table);
        //document.add(new Chunk("\n"));
        Paragraph p = new Paragraph();
        //Invoice header
        Paragraph invoiceHeader = new Paragraph("INVOICE",largefont);
        invoiceHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(invoiceHeader);
        //Invoice Details Table
        PdfPTable tblDetails = new PdfPTable(3);
        PdfPCell billedTo = new PdfPCell(new Phrase("Billed To:",medfont));
        billedTo.disableBorderSide(Rectangle.RIGHT);
        tblDetails.addCell(billedTo);
        PdfPCell emptyCell = new PdfPCell();
        tblDetails.addCell(emptyCell);
        PdfPCell toAddress = new PdfPCell(buildAddressPhrase(this.inputData.getCustomerDetails().getFullAddress()));
        toAddress.disableBorderSide(Rectangle.LEFT);
        tblDetails.addCell(toAddress);
        tblDetails.completeRow();
        PdfPCell lblInvoiceNo = new PdfPCell(new Phrase("Invoice No: "+inputData.getInvoiceId(),medfont));
        tblDetails.addCell(lblInvoiceNo);
        PdfPCell lblDate = new PdfPCell(new Phrase(inputData.getDate().toString(),medfont));
        tblDetails.addCell(lblDate);
        tblDetails.completeRow();
        document.add(tblDetails);
        buildOrder(document);
        document.close();
        return Files.readAllBytes(Paths.get(pathToPdf));
    }

    private String splitbycomma(String address) {
        String[] arr=  address.split(",");
        java.util.List<String> lst = Arrays.asList(arr);
        return lst.stream()
                .map( Object::toString )
                .collect( Collectors.joining( "\n" ) );
    }

    private Phrase buildAddressPhrase(String address) {
        Phrase phrase = new Phrase();
        Chunk add1 = new Chunk(address,medfont);
        phrase.add(add1);
        return phrase;
    }

    private  void buildOrder(final Document document) throws DocumentException {
        document.add(new Chunk("\n"));
        Paragraph gstlbl = new Paragraph();
        gstlbl.add(new Phrase("GSTNum:"+saleslocationEntity.getGstno(),medfont));
        gstlbl.setAlignment(Element.ALIGN_CENTER);
        document.add(gstlbl);
        document.add(new Chunk("\n"));
        document.add(new Chunk("\n"));
        Paragraph lbl = new Paragraph();
        lbl.add(new Phrase("Order Summary",medfont));
        lbl.setAlignment(Element.ALIGN_CENTER);
        document.add(lbl);
        document.add(new Chunk("\n"));
        InvoiceDetails invoiceDetails = inputData;
        PdfPTable table = new PdfPTable(5);

        //addheader

        PdfPCell h1 = new PdfPCell(new Phrase("Product Code",medfont));
        table.addCell(h1);
        PdfPCell h2 = new PdfPCell(new Phrase("Description",medfont));
        table.addCell(h2);
        PdfPCell h3 = new PdfPCell(new Phrase("Price(per unit)",medfont));
        table.addCell(h3);
        PdfPCell h4 = new PdfPCell(new Phrase("Quantity",medfont));
        table.addCell(h4);
        PdfPCell h5 = new PdfPCell(new Phrase("Total(Price*Qty)",medfont));
        table.addCell(h5);
        table.completeRow();

        double totalPrice = 0.0;
        for (ProductCartItem item: invoiceDetails.getProductCartItems()
                ) {
            PdfPCell name = new PdfPCell(new Phrase(item.getProduct().getCode(),medfont));
            table.addCell(name);
            PdfPCell descripton = new PdfPCell(new Phrase(item.getProduct().getDescription(),medfont));
            table.addCell(descripton);
            PdfPCell price = new PdfPCell(new Phrase(Double.toString(item.getProduct().getPrice()),medfont));
            table.addCell(price);
            PdfPCell qty = new PdfPCell(new Phrase(Integer.toString(item.getQuantity()),medfont));
            table.addCell(qty);
            double itemTotal=item.getQuantity()*item.getProduct().getPrice();
            PdfPCell total = new PdfPCell(new Phrase(Double.toString(itemTotal),medfont));
            table.addCell(total);
            totalPrice += itemTotal;
            table.completeRow();
        }

        PdfPCell name = new PdfPCell(new Phrase());
        table.addCell(name);
        PdfPCell descripton = new PdfPCell();
        table.addCell(descripton);
        PdfPCell price = new PdfPCell();
        table.addCell(price);
        PdfPCell qty = new PdfPCell(new Phrase("Total ",medfont));
        table.addCell(qty);
        PdfPCell total = new PdfPCell(new Phrase(Double.toString(totalPrice),medfont));
        table.addCell(total);
        table.completeRow();
        document.add(table);
    }
}
