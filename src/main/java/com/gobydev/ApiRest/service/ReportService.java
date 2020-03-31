package com.gobydev.ApiRest.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.gobydev.ApiRest.model.Product;
import com.gobydev.ApiRest.repository.IProductRespository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
	
	@Autowired
	IProductRespository repository;
	
	public String exportReport(String reportFormat) {
		
		List<Product> productos = repository.findAll();
		
		String path = "H:\\Proyectos web\\JasperReports";
		
		//load file and compile it
		try {
			
			File file = ResourceUtils.getFile("classpath:productos.jrxml");
			
			JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(productos);
			
			Map<String, Object> parameter = new HashMap<>();
			
			parameter.put("Creado por ", "GobyDev");
			
			JasperPrint jp = JasperFillManager.fillReport(jr, parameter,data);
			
			if (reportFormat.equalsIgnoreCase("html")) {
				
				JasperExportManager.exportReportToHtmlFile(jp, path + "\\productos.html");
				
			}
			
			if (reportFormat.equalsIgnoreCase("pdf")) {
				
				JasperExportManager.exportReportToPdfFile(jp, path +"\\productos.pdf");
				
			}
			
			return "reporte creado en la ruta: "+ path;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error al crear el pdf";
	}

}
