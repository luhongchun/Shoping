package com.example.demo1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo1.entity.ZhiqiShop;

public interface ZhiqiShopRepository extends CrudRepository<ZhiqiShop, String> {
	
    List<ZhiqiShop> findAllByProductName(String productName);
    
}
