package com.styros.esales.service;
/**
 * Created by atul on 11/21/2017.
 */
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.styros.esales.model.InvoiceDetails;
import com.styros.esales.model.Product;
import com.styros.esales.model.ProductCartItem;

import java.io.File;
import java.io.FileOutputStream;

public class PdfGeneration {

    static Font medfont = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
    static Font largefont = new Font(Font.FontFamily.HELVETICA,20,Font.NORMAL,BaseColor.LIGHT_GRAY);
    public static void tableWithCells(String pathToPdf)throws Exception{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathToPdf));
        document.open();

        PdfPTable table = new PdfPTable(4);
        PdfPCell cell1 = new PdfPCell(new Phrase("Billed By:",medfont));
        cell1.disableBorderSide(Rectangle.RIGHT);
        table.addCell(cell1);
        Phrase address = buildAddressPhrase();
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
        //document.add(new Chunk("\n"));
        //Invoice Details Table
        PdfPTable tblDetails = new PdfPTable(3);
        PdfPCell billedTo = new PdfPCell(new Phrase("Billed To:",medfont));
        billedTo.disableBorderSide(Rectangle.RIGHT);
        tblDetails.addCell(billedTo);
        PdfPCell emptyCell = new PdfPCell();
        tblDetails.addCell(emptyCell);
        PdfPCell toAddress = new PdfPCell(buildAddressPhrase());
        toAddress.disableBorderSide(Rectangle.LEFT);
        tblDetails.addCell(toAddress);
        tblDetails.completeRow();
        PdfPCell lblInvoiceNo = new PdfPCell(new Phrase("Invoice No:  INV00110223",medfont));
        tblDetails.addCell(lblInvoiceNo);
        PdfPCell lblDate = new PdfPCell(new Phrase("Date: 17-Nov-2017:3:30 PM IST",medfont));
        tblDetails.addCell(lblDate);
        tblDetails.completeRow();
        document.add(tblDetails);
        buildOrder(document);
        
        document.close();
    }

    private static void buildOrder(final Document document) throws DocumentException {



        document.add(new Chunk("\n"));
        Paragraph lbl = new Paragraph();
        lbl.setAlignment(Element.ALIGN_CENTER);
        lbl.add(new Phrase("Order Summary",medfont));
        document.add(lbl);
        document.add(new Chunk("\n"));
        InvoiceDetails invoiceDetails = getSampleInvoiceData();
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
            PdfPCell total = new PdfPCell(new Phrase(Double.toString(item.getTotalPrice()),medfont));
            table.addCell(total);
            table.completeRow();
        }
        document.add(table);

    }

    private static InvoiceDetails getSampleInvoiceData() {

        InvoiceDetails invoiceDetails = new InvoiceDetails();

        ProductCartItem item1 = new ProductCartItem();
        Product prod1 = new Product();
        prod1.setCode("SPRITE");
        prod1.setDescription("Beverage");
        prod1.setPrice(25.0);
        item1.setProduct(prod1);
        item1.setQuantity(2);


        ProductCartItem item2 = new ProductCartItem();
        Product prod2 = new Product();
        prod2.setCode("COKE");
        prod2.setDescription("Beverage");
        prod2.setPrice(25.0);
        item2.setProduct(prod2);item1.setQuantity(2);
        item2.setQuantity(5);
        invoiceDetails.getProductCartItems().add(item1);
        invoiceDetails.getProductCartItems().add(item2);

        return invoiceDetails;

    }

    private static Phrase buildAddressPhrase() {
        Phrase address = new Phrase();
        Chunk add1 = new Chunk("Atul Sharma\n",medfont);
        Chunk add2 = new Chunk("House No. 211 \n",medfont);
        Chunk add3 = new Chunk("Nilgiri Apartments \n",medfont);
        Chunk add4 = new Chunk("Alaknanda,Delhi(110019)\n",medfont);
        Chunk add5 = new Chunk("ContactNo.:9711709581\n",medfont);
        address.add(add1);
        address.add(add2);
        address.add(add3);
        address.add(add4);
        address.add(add5);
        return address;
    }

    public void generateRectangle(String pathToPdf)throws Exception{
        File f = new File(pathToPdf);
        if(f.exists())
            f.delete();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pathToPdf));
        document.open();

        PdfContentByte canvas = writer.getDirectContent();
        Rectangle rect = new Rectangle(36, 36, 559, 806);
        rect.setBorder(Rectangle.BOX);
        rect.setBorderWidth(2);
        canvas.rectangle(rect);
        document.close();
    }


    public void generateSample(String pathToPdf) throws Exception{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pathToPdf));
        writer.setViewerPreferences(PdfWriter.PageModeUseOC);
        writer.setPdfVersion(PdfWriter.VERSION_1_5);
        document.open();

        PdfLayer layer = new PdfLayer("Do you see me?", writer);
        layer.setOn(true);
        BaseFont bf = BaseFont.createFont();
        PdfContentByte cb = writer.getDirectContent();
        cb.beginText();
        cb.setFontAndSize(bf, 18);
        cb.showTextAligned(Element.ALIGN_LEFT, "Do you see me?", 50, 790, 0);
        cb.beginLayer(layer);
        cb.showTextAligned(Element.ALIGN_LEFT, "Peek-a-Boo!!!", 50, 766, 0);
        cb.endLayer();
        cb.endText();
        // step 5
        document.close();
    }

    public static void simpleChunk(String path)throws Exception{
        File f = new File(path);
        if(f.exists())
            f.delete();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        writer.setInitialLeading(16);
        document.open();
        document.add(new Chunk("hi this is atul."));
        document.add(new Chunk("\n"));
        document.add(new Chunk("Testing newline.hi this is atul."));

        Font font = new Font(Font.FontFamily.HELVETICA,6,Font.BOLD,BaseColor.GREEN);
        Chunk id=new Chunk("Chunk with helvetica size 6 bold green",font);
        id.setBackground(BaseColor.BLACK,1f,0.5f,1f,1.5f);
        id.setTextRise(6);
        document.add(id);
        document.close();
    }

    public static void tabbingExample(String path)throws Exception{
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();

        Paragraph p = new Paragraph("Hello World.");

        document.add(p);

        p = new Paragraph();
        p.setTabSettings(new TabSettings(56f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Hello World with tab."));

        document.add(p);

        p = new Paragraph();
        p.add(new Chunk("Hello World with"));
        p.setTabSettings(new TabSettings(56f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("an inline tab."));

        document.add(p);

        document.close();
    }

}
