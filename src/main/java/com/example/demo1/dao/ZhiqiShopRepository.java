package com.example.demo1.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo1.entity.Shoping;

public interface ZhiqiShopRepository extends CrudRepository<Shoping, String> {
	
	List<Shoping> findAllByPhoneNum(String phoneNum);//根据电话查询
    Shoping findByPhoneNumAndProductName(String productNum,String productName);//根据电话和产品名进行查询 
}
