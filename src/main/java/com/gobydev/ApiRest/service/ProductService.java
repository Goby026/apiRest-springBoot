package com.gobydev.ApiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gobydev.ApiRest.model.Product;
import com.gobydev.ApiRest.repository.IProductRespository;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	IProductRespository repository;

	@Override
	public List<Product> listAll() {
		List<Product> products = repository.findAll();
		return products;
	}

	@Override
	public Product save(Product p) {
		Product prod =repository.save(p);
		return prod;
	}

	@Override
	public Product get(int id) {
		return repository.findById(id).get();
	}

	@Override
	public void delete(int id) {
		repository.findById(id).ifPresent((x)->{
//			System.out.print(x);
			repository.deleteById(id);
		});
	}
	

}
