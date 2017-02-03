package com.weicent.web.domain;

/**
 * 
 */
public class Buys1{
   	
   	public Buys1() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String usersDetail;
    public String getUsersDetail(){
         return usersDetail;
    }  
	public void setUsersDetail(String usersDetail) {
		this.usersDetail = usersDetail;
	}
	//
	private String buysName;
    public String getBuysName(){
         return buysName;
    }  
	public void setBuysName(String buysname) {
		this.buysName = buysname;
	}
	//
	private String buysPrice;
    public String getBuysPrice(){
         return buysPrice;
    }  
	public void setBuysPrice(String buysprice) {
		this.buysPrice = buysprice;
	}
	//
	private String buysAddress;
    public String getBuysAddress(){
         return buysAddress;
    }  
	public void setBuysAddress(String buysaddress) {
		this.buysAddress = buysaddress;
	}
	//
	private String buysDetail;
    public String getBuysDetail(){
         return buysDetail;
    }  
	public void setBuysDetail(String buysdetail) {
		this.buysDetail = buysdetail;
	}
	//
	private String buysPhone;
    public String getBuysPhone(){
         return buysPhone;
    }  
	public void setBuysPhone(String buysphone) {
		this.buysPhone = buysphone;
	}
	//
	private String buysAddTime;
    public String getBuysAddTime(){
         return buysAddTime;
    }  
	public void setBuysAddTime(String buysaddtime) {
		this.buysAddTime = buysaddtime;
	}
	//
	private Integer buysStatus;
    public Integer getBuysStatus(){
         return buysStatus;
    }  
	public void setBuysStatus(Integer buysstatus) {
		this.buysStatus = buysstatus;
	}
	//
	private Integer buysQQ;
    public Integer getBuysQQ(){
         return buysQQ;
    }  
	public void setBuysQQ(Integer buysqq) {
		this.buysQQ = buysqq;
	}
	
	@Override
	public String toString() {
		return "Buys1 [id=" + id + ", usersDetail=" + usersDetail
				+ ", buysName=" + buysName + ", buysPrice=" + buysPrice
				+ ", buysAddress=" + buysAddress + ", buysDetail=" + buysDetail
				+ ", buysPhone=" + buysPhone + ", buysAddTime=" + buysAddTime
				+ ", buysStatus=" + buysStatus + ", buysQQ=" + buysQQ + "]";
	}
	   
}