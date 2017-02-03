package com.weicent.web.tool;

//import com.essay.minireader.web.request.BaseRequest;
import com.google.gson.Gson;

//获取请求的Json，并转换成对象
public class RequestObjectForJson<T extends Object> {
	 private Class<T> clazz;
	 private Gson gson;

	 public RequestObjectForJson(Class<T> clazz) {
	  this.clazz = clazz;
	  gson = new Gson();
	 }

	 public T getfromJson(String responseString) {
	  T t = gson.fromJson(responseString, clazz);
	  return t;
	 }
}
