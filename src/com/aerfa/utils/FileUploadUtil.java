package com.aerfa.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

public class FileUploadUtil {
	private FileUploadUtil() {}
	
	public static final String FILE_URI="fileUri";
	public static final String FILE_NAME="fileName";
	//文件访问路径前缀
	private static final String ACCESS_URLPREFX="http://192.168.40.128:8888/";
	
	
	/**
	 * 文件上传,带普通表单项
	 * @param request
	 * @param response
	 * @param args 要获取的普通表单项
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static Map<String,String> uploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding( "utf-8" );
		response.setHeader("Content-Type" , "text/html");
		String rootPath = request.getServletContext().getRealPath( "/" );
		//存储普通表单项的值
		Map<String,String> map = new HashMap<>(16);
		
		/*
		 * 1，创建磁盘文件项工厂 作用：设置缓存文件的大小；设置临时文件存储的位置
		 */
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置缓存文件的大小
		factory.setSizeThreshold(1024 * 1024);
		// 临时目录temp的绝对路径
//		String path = request.getServletContext().getRealPath("/temp");
		// 设置临时文件存储的位置
//		factory.setRepository(new File(path));

		/* 2，创建文件上传核心类 */

		ServletFileUpload upload = new ServletFileUpload(factory);
		// 解决上传文件的名称乱码
		upload.setHeaderEncoding("utf-8");
		// 判断是不是文件上传型表单
		boolean multipartContent = upload.isMultipartContent(request);

		/* 3，分析request */
		// 若是文件上传型表单 (注意：在文件上传型表单中，request.getParameter相关方法失效)
		if (multipartContent) {
			System.out.println(" multiparContent...........");
			// 文件项集合，（我的理解是表单项集合，可能不准确）
			List<FileItem> parseRequest = null;
			try {
				parseRequest = upload.parseRequest(request);
			} catch (FileUploadException e) { e.printStackTrace(); }

			if (parseRequest != null) {
				
				// 遍历文件项集合
				for (FileItem item : parseRequest) {
					// 判断是不是普通表单项
					boolean isformField = item.isFormField();
					System.out.println("遍历文件项集合..........");
					if (isformField) {
						// 若是普通表单项
						String arg = item.getFieldName();
						
						String value = item.getString("utf-8");
						map.put(arg, value);
					}else {
						// 若是文件表单项
						// 获取上传文件名
						String[] split = item.getName().split("/");
						String fileName = split[split.length - 1];
						
						if(fileName==null || fileName.equals(""))
							continue;
						
						//存入FastDFS，并返回url
						byte[] bs = item.get();
						String ext = fileName.substring(fileName.lastIndexOf(".")+1);
						//存入服务器
						String fileUri = uploadSave(bs,fileName,ext);
						
						//返回客户端图片访问路径
						if(request.getRequestURI().contains("fileUpload")) {
							response.getWriter().write("{\"uploaded\":1,\"url\":\""+fileUri+"\"}");
						}else {
							//返回附件访问路径
							map.put(FILE_URI, fileUri);
							map.put(FILE_NAME,fileName);
						}
						// 删除temp下临时文件
						item.delete();
					}
				}
			}
			
		}else {
		}
			
		return map;
	}
	
	/**
	 * 文件上传到服务器
	 */
	private static String uploadSave(byte[] fileByte,String fileName,String fileExt) {
		 // 拼接服务区的文件路径
	     StringBuffer urlStr = new StringBuffer();
	     urlStr.append(ACCESS_URLPREFX);
	     
	     System.out.println("[ fileName = "+fileName+", fileExt = "+fileExt+" ]");
	     try {
	     // 加载配置文件
			ClientGlobal.initByProperties("fastDFS.properties");
			
	    	 // 链接FastDFS服务器，创建tracker和Stroage
	    	 TrackerClient trackerClient = new TrackerClient();
	         TrackerServer trackerServer = trackerClient.getConnection();
	         StorageClient storageClient = new StorageClient(trackerServer,null);

	    	 //利用字节流上传文件
	    	 //NameValuePair[] nvps = new NameValuePair[1];
	    	 //nvps[0] = new NameValuePair(file_Name, ext_file);
	    	 
	    	 String[] strarr = storageClient.upload_file(fileByte, fileExt, null);
	    	 
	    	 for(String str:strarr) {
	    		 urlStr.append("/"+str);
	    	 }
	     } catch (IOException | MyException e) { e.printStackTrace(); }
   	// 返回数组，包含组名和图片的路径
		return urlStr.toString();
	}
}
