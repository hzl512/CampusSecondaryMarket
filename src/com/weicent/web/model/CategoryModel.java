﻿package com.weicent.web.model;

/**
 * 
 */
public class CategoryModel{
   	
   	public CategoryModel() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String categoryName;
    public String getCategoryName(){
         return categoryName;
    }  
	public void setCategoryName(String categoryname) {
		this.categoryName = categoryname;
	}
	//
	private String categoryRemark;
    public String getCategoryRemark(){
         return categoryRemark;
    }  
	public void setCategoryRemark(String categoryremark) {
		this.categoryRemark = categoryremark;
	}
	   
}