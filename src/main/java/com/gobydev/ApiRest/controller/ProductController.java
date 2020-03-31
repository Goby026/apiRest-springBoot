package com.gobydev.ApiRest.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gobydev.ApiRest.model.Product;
import com.gobydev.ApiRest.service.ProductService;
import com.gobydev.ApiRest.service.ReportService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private ReportService report;
	
	@GetMapping("/products")
	public List<Product> list(){
		return service.listAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get( @PathVariable(value = "id") int id) {		 
		try {
			
			Product prod = service.get(id);
			
			return new ResponseEntity<Product>(prod, HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> add( @RequestBody Product p) {
		
//		return new ResponseEntity<Product>(p, HttpStatus.FOUND);
		
		try {
			
			Product prod = service.save(p);
			
			return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
			
		} catch (NoSuchElementException e) {
			
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	//se debe enviar el objeto completo, incluyendo el id, de lo contrario creará un registro nuevo
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> update( @RequestBody Product p ,@PathVariable int id ){
		try {
			Product existProduct = service.get(id);
			service.save(p);
			return new ResponseEntity<Product>(p,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> delete( @PathVariable int id){
		try {
			service.delete(id);
			return new ResponseEntity<Product>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	
//	@RequestMapping(value = "/addProduct", method=RequestMethod.POST,headers = "Accept=*/*",produces = "application/json", consumes="application/json")
//	public String greetingSubmit(@RequestBody Product p1) {
//	    return "result: " + p1;
//	}
	
	//REPOTE DE PRODUCTOS
	@GetMapping("/reporte/{format}")
	public String generateReport(@PathVariable String format) {		
		return report.exportReport(format);
	}
	

}
