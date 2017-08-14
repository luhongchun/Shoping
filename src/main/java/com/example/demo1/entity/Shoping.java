package com.example.demo1.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "zhiqi_shop")
public class Shoping {
	@Id
    @GeneratedValue(generator = "uuidGenerator")
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	private String id;
	private String skuUniqueCode;
	private String productTid;
	@NotNull 
	private String phoneNum;
	private String phoneNum2;
	private String phoneNum3;
	@NotNull 
	private String productName;
	private String productPicPath;
	private String childName;
	private String childClass;
	private Date   createdTime;
	private Date   endTime;
	private int userCnt;
	private int productMonth;
	private int num;
	private Float productSumPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSkuUniqueCode() {
		return skuUniqueCode;
	}
	public void setSkuUniqueCode(String skuUniqueCode) {
		this.skuUniqueCode = skuUniqueCode;
	}
	public String getProductTid() {
		return productTid;
	}
	public void setProductTid(String productTid) {
		this.productTid = productTid;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPhoneNum2() {
		return phoneNum2;
	}
	public void setPhoneNum2(String phoneNum2) {
		this.phoneNum2 = phoneNum2;
	}
	public String getPhoneNum3() {
		return phoneNum3;
	}
	public void setPhoneNum3(String phoneNum3) {
		this.phoneNum3 = phoneNum3;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPicPath() {
		return productPicPath;
	}
	public void setProductPicPath(String productPicPath) {
		this.productPicPath = productPicPath;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	public String getChildClass() {
		return childClass;
	}
	public void setChildClass(String childClass) {
		this.childClass = childClass;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getUserCnt() {
		return userCnt;
	}
	public void setUserCnt(int userCnt) {
		this.userCnt = userCnt;
	}
	public int getProductMonth() {
		return productMonth;
	}
	public void setProductMonth(int productMonth) {
		this.productMonth = productMonth;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Float getProductSumPrice() {
		return productSumPrice;
	}
	public void setProductSumPrice(Float productSumPrice) {
		this.productSumPrice = productSumPrice;
	}
	
	

}
