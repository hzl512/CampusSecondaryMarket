package com.weicent.web.tool;

import com.google.gson.Gson;

//创建json字符串
public class ResponseJsonForObject {
	public static String createJsonString(Object object) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(object); // 用Gson方式 把object 保存为 json字符串
		return jsonString;
	}
}
