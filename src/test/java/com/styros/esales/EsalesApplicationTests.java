package com.styros.esales;

import com.styros.esales.entity.RoutesEntity;
import com.styros.esales.repository.RoutesRepository;
import com.styros.esales.service.PdfGeneration;
import com.styros.esales.service.ProductService;
import com.styros.esales.utils.JsonHelper;
import com.styros.esales.utils.Location;
import com.styros.esales.utils.RayCastAlgo;
import com.styros.esales.utils.Vertex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsalesApplicationTests {

    static String path="D://sample.pdf";

    @Autowired
    RoutesRepository repository;

    public void testRayCast(){
        RoutesEntity route= repository.findByname("alaknanda");
        RayCastAlgo algo = new RayCastAlgo();
        algo.point = new Vertex();

        algo.point.X=28.52762160224303;
        algo.point.Y = 77.25516915318622;
        Location[] locations= JsonHelper.convertToType(route.getData(),Location[].class);
/*        algo.point.X = locations[0].lat;
        algo.point.Y = locations[0].lng;*/
        List<Location> lstLoc = Arrays.asList(locations);
        List<Vertex> vertices = new ArrayList<>();
        lstLoc.forEach(x->{
            Vertex v = new Vertex();
            v.X = x.lat;
            v.Y = x.lng;
            vertices.add(v);
        });
        algo.lstvertices = vertices;
        boolean result= algo.isInside();
    }


    public void testRoutesRepo(){
        Object route=repository.findById(23);
    }
    @Test
    public void tableWithCells(){
        try {
            //PdfGeneration.tableWithCells(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //@Test
    public void tabbing(){
        try {
            PdfGeneration.tabbingExample(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Test
    public void simpleCell(){
        try{
            PdfGeneration.simpleChunk(path);
        }
        catch (Exception ex){

        }
    }
	//@Test
	public void contextLoads() {
		ProductService service = new ProductService();
		service.getProducts();
	}

	//@Test
	public void testPdfGeneration(){
		PdfGeneration pdfGeneration = new PdfGeneration();
		try {
			pdfGeneration.generateSample("D://sample.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Test
    public void testPdfRect() {
        try {
            PdfGeneration pdfGeneration = new PdfGeneration();
            pdfGeneration.generateRectangle(path);
        } catch (Exception ex) {

        }
    }
}
