package com.weicent.web.tool;

import java.io.*;
import java.util.Date;

/**
 * 文件上传操作 
 */
public class UploadFile {
	public String upload(String dir, File formFile) throws Exception {
		Date date = new Date();
		// 取欲上传的文件的名字和长度
		String fname = formFile.getName();
		// 将上传时间加入文件名
		int i = fname.indexOf(".");
		String name = String.valueOf(date.getTime());
		String type = fname.substring(i + 1);
		fname = name + "." + type;
		InputStream streamIn = new FileInputStream(formFile); // 创建读取用户上传文件的对象
		File uploadFile = new File(dir); // 创建把上传数据写到目标文件的对象
		if (!uploadFile.exists() || uploadFile == null) { // 判断指定路径是否存在，不存在则创建路径
			uploadFile.mkdirs();
		}
		String path = uploadFile.getPath() + "/" + fname;
		OutputStream streamOut = new FileOutputStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
		return fname;
	}
	
	
	public String upload1(String dir, InputStream is,String fileName) throws Exception {
		Date date = new Date();
		// 取欲上传的文件的名字和长度
		String fname = fileName;
		// 将上传时间加入文件名
		int i = fname.indexOf(".");
		String name = String.valueOf(date.getTime());
		String type = fname.substring(i + 1);
		fname = name + "." + type;
		
		File uploadFile = new File(dir); // 创建把上传数据写到目标文件的对象
		if (!uploadFile.exists() || uploadFile == null) { // 判断指定路径是否存在，不存在则创建路径
			uploadFile.mkdirs();
		}
		String path = uploadFile.getPath() + "/" + fname;
		BufferedInputStream fis = new BufferedInputStream(is);
		FileOutputStream fos = new FileOutputStream(path);
		int f;
		while ((f = fis.read()) != -1) {
			fos.write(f);
		}
		fos.flush();
		fos.close();
		fis.close();
		
		return fname;
	}
	
	//上传apk
	public String upload2(String dir, File formFile) throws Exception {
		String fname = formFile.getName();
		InputStream streamIn = new FileInputStream(formFile); // 创建读取用户上传文件的对象
		File uploadFile = new File(dir); // 创建把上传数据写到目标文件的对象
		if (!uploadFile.exists() || uploadFile == null) { // 判断指定路径是否存在，不存在则创建路径
			uploadFile.mkdirs();
		}
		String path = uploadFile.getPath() + "/" + fname;
		OutputStream streamOut = new FileOutputStream(path);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
		while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
			streamOut.write(buffer, 0, bytesRead);
		}
		streamOut.close();
		streamIn.close();
		return fname;
	}
}
