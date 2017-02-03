package com.weicent.web.domain;


/**
 * 
 */
public class Commodity1{
   	
   	public Commodity1() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private Integer usersID;
    public Integer getUsersID(){
         return usersID;
    }  
	public void setUsersID(Integer usersid) {
		this.usersID = usersid;
	}
	//用户信息
	private String usersDetail;
    public String getUsersDetail(){
         return usersDetail;
    }  
	public void setUsersDetail(String usersDetail) {
		this.usersDetail = usersDetail;
	}
	//用户专业信息
	private String usersProfessionNameGrade;
    public String getUsersProfessionNameGrade(){
         return usersProfessionNameGrade;
    }  
	public void setUsersProfessionNameGrade(String sersProfessionNameGrade) {
		this.usersProfessionNameGrade = sersProfessionNameGrade;
	}
	//图片地址
	private String commodityImageUrl;
    public String getCommodityImageUrl(){
         return commodityImageUrl;
    }  
	public void setCommodityImageUrl(String commodityimageurl) {
		this.commodityImageUrl = commodityimageurl;
	}
	//商品名称
	private String commodityName;
    public String getCommodityName(){
         return commodityName;
    }  
	public void setCommodityName(String commodityname) {
		this.commodityName = commodityname;
	}
	//商品详情
	private String commodityDetail;
    public String getCommodityDetail(){
         return commodityDetail;
    }  
	public void setCommodityDetail(String commoditydetail) {
		this.commodityDetail = commoditydetail;
	}
	//交易地点
	private String commodityAddress;
    public String getCommodityAddress(){
         return commodityAddress;
    }  
	public void setCommodityAddress(String commodityaddress) {
		this.commodityAddress = commodityaddress;
	}
	//价格
	private String commodityPrice;
    public String getCommodityPrice(){
         return commodityPrice;
    }  
	public void setCommodityPrice(String commodityprice) {
		this.commodityPrice = commodityprice;
	}
	//讲价
	private Integer commodityBargain;
    public Integer getCommodityBargain(){
         return commodityBargain;
    }  
	public void setCommodityBargain(Integer commoditybargain) {
		this.commodityBargain = commoditybargain;
	}
	//联系电话
	private Integer commodityPhone;
    public Integer getCommodityPhone(){
         return commodityPhone;
    }  
	public void setCommodityPhone(Integer commodityphone) {
		this.commodityPhone = commodityphone;
	}
	//联系QQ
	private Integer commodityQQ;
    public Integer getCommodityQQ(){
         return commodityQQ;
    }  
	public void setCommodityQQ(Integer commodityqq) {
		this.commodityQQ = commodityqq;
	}
	//添加时间
	private String commodityAddTime;
    public String getCommodityAddTime(){
         return commodityAddTime;
    }  
	public void setCommodityAddTime(String commodityaddtime) {
		this.commodityAddTime = commodityaddtime;
	}
	//浏览量
	private Integer commodityViews;
    public Integer getCommodityViews(){
         return commodityViews;
    }  
	public void setCommodityViews(Integer commodityviews) {
		this.commodityViews = commodityviews;
	}
	   
}