package com.weicent.web.model;

/**
 * 
 */
public class UsersModel{
   	
   	public UsersModel() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String usersName;
    public String getUsersName(){
         return usersName;
    }  
	public void setUsersName(String usersname) {
		this.usersName = usersname;
	}
	//
	private String usersPwd;
    public String getUsersPwd(){
         return usersPwd;
    }  
	public void setUsersPwd(String userspwd) {
		this.usersPwd = userspwd;
	}
	//
	private String usersAddTime;
    public String getUsersAddTime(){
         return usersAddTime;
    }  
	public void setUsersAddTime(String usersaddtime) {
		this.usersAddTime = usersaddtime;
	}
	//
	private String usersNickName;
    public String getUsersNickName(){
         return usersNickName;
    }  
	public void setUsersNickName(String usersnickname) {
		this.usersNickName = usersnickname;
	}
	//
	private String usersPhone;
    public String getUsersPhone(){
         return usersPhone;
    }  
	public void setUsersPhone(String usersphone) {
		this.usersPhone = usersphone;
	}
	//
	private String usersQQ;
    public String getUsersQQ(){
         return usersQQ;
    }  
	public void setUsersQQ(String usersqq) {
		this.usersQQ = usersqq;
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
	private Integer professionID;
    public Integer getProfessionID(){
         return professionID;
    }  
	public void setProfessionID(Integer professionid) {
		this.professionID = professionid;
	}
	//
	private Integer usersGrade;
    public Integer getUsersGrade(){
         return usersGrade;
    }  
	public void setUsersGrade(Integer usersgrade) {
		this.usersGrade = usersgrade;
	}
	   
}