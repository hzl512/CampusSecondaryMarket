﻿package com.weicent.web.domain;

/**
 * 
 */
public class Departments{
   	
   	public Departments() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String departmentsName;
    public String getDepartmentsName(){
         return departmentsName;
    }  
	public void setDepartmentsName(String departmentsname) {
		this.departmentsName = departmentsname;
	}
	//
	private String departmentsRemark;
    public String getDepartmentsRemark(){
         return departmentsRemark;
    }  
	public void setDepartmentsRemark(String departmentsremark) {
		this.departmentsRemark = departmentsremark;
	}
	   
}