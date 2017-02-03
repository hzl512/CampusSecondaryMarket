package com.weicent.web.model;

/**
 * 
 */
public class ManageModel{
   	
   	public ManageModel() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String manageName;
    public String getManageName(){
         return manageName;
    }  
	public void setManageName(String managename) {
		this.manageName = managename;
	}
	//
	private String managePassWord;
    public String getManagePassWord(){
         return managePassWord;
    }  
	public void setManagePassWord(String managepassword) {
		this.managePassWord = managepassword;
	}
	//
	private String manageRemark;
    public String getManageRemark(){
         return manageRemark;
    }  
	public void setManageRemark(String manageremark) {
		this.manageRemark = manageremark;
	}
	   
}