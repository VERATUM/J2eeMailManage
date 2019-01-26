package com.aerfa.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Filee {
	
	/**
	 * 文件上传封装,获取List<FileItem>对象
	 * @param req请求
	 * @param res响应
	 * @return	List<FileItem>通过位置取值
	 */
	public List<FileItem> fileUp (HttpServletRequest req, HttpServletResponse res) {
		//设置编码格式
		try {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/jsp;chaset=UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		//创建FileItemFactory对象
		FileItemFactory fif = new DiskFileItemFactory();
		//创建ServletFileUpLoad对象
		ServletFileUpload sfu = new ServletFileUpload(fif);
		//解析请求
		List<FileItem>itemList = null;
		try {
			itemList = sfu.parseRequest(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemList;
	}
	//上传文件
	/**
	 * 上传文件
	 * @param req 请求
	 * @param res 响应
	 * @param itemList List<FileItem>集合
	 * @param index 文件上传在表单中的位置
	 * @return 储存路径
	 */
	public String upFile(HttpServletRequest req, HttpServletResponse res,List<FileItem>itemList,int index) {
					//处理文件域
					FileItem item = itemList.get(index);
					//设置保存路径
					String savePath =req.getRealPath("load") ;
					//获取文件名
					String fileName = item.getName();
					//获取后缀名
					String extName = fileName.substring(fileName.lastIndexOf("."));		
					//保存文件名
					String saveName = System.currentTimeMillis()+extName;
					//创建保存对象
					File saveFile = new File(savePath+"\\"+saveName);
					//上传
					try {
						item.write(saveFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				return saveName;
	}
}
