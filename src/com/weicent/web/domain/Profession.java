package com.weicent.web.domain;

/**
 * 
 */
public class Profession{
   	
   	public Profession() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private Integer departmentsID;
    public Integer getDepartmentsID(){
         return departmentsID;
    }  
	public void setDepartmentsID(Integer departmentsid) {
		this.departmentsID = departmentsid;
	}
	//
	private String professionName;
    public String getProfessionName(){
         return professionName;
    }  
	public void setProfessionName(String professionname) {
		this.professionName = professionname;
	}
	//
	private String professionRemark;
    public String getProfessionRemark(){
         return professionRemark;
    }  
	public void setProfessionRemark(String professionremark) {
		this.professionRemark = professionremark;
	}
	   
}