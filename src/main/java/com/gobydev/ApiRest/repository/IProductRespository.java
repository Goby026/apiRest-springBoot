package com.gobydev.ApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gobydev.ApiRest.model.Product;

public interface IProductRespository extends JpaRepository<Product, Integer> {

}
