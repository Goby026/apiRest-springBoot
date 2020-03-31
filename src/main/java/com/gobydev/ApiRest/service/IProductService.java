package com.gobydev.ApiRest.service;

import java.util.List;

import com.gobydev.ApiRest.model.Product;

public interface IProductService {
	
	public List<Product> listAll();
	
	public Product save( Product p );
	
	public Product get( int id );
	
	public void delete( int id );

}