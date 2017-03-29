package com.weicent.web.model;

/**
 * 
 */
public class UpdateModel{
   	
   	public UpdateModel() {}
	     
    //
	private Integer id;
    public Integer getId(){
         return id;
    }  
	public void setId(Integer id) {
		this.id = id;
	}
	//
	private String versionName;
    public String getVersionName(){
         return versionName;
    }  
	public void setVersionName(String versionname) {
		this.versionName = versionname;
	}
	//
	private String versionNumber;
    public String getVersionNumber(){
         return versionNumber;
    }  
	public void setVersionNumber(String versionnumber) {
		this.versionNumber = versionnumber;
	}
	//
	private Integer forcedUpdate;
    public Integer getForcedUpdate(){
         return forcedUpdate;
    }  
	public void setForcedUpdate(Integer forcedupdate) {
		this.forcedUpdate = forcedupdate;
	}
	//
	private String description;
    public String getDescription(){
         return description;
    }  
	public void setDescription(String description) {
		this.description = description;
	}
	//
	private String url;
    public String getUrl(){
         return url;
    }  
	public void setUrl(String url) {
		this.url = url;
	}
	//
	private String createTime;
    public String getCreateTime(){
         return createTime;
    }  
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}
	//
	private String size;
    public String getSize(){
         return size;
    }  
	public void setSize(String size) {
		this.size = size;
	}
	   
}