package com.aerfa.dao;

import java.util.List;

import com.aerfa.entity.FileInfo;

public interface FileInfoDao {
	    //添加
		public int addFileInfo(FileInfo fileInfo);
		//删除
		public int deleteFileInfo(Integer fileInfoid);
		//修改
		public int updateFileInfo(FileInfo fileInfo);
		//查所有
		public List<FileInfo> selectFileInfo();
		//查单条
		public FileInfo selecOneFileInfo(Integer fileInfoid);
		
		//查询邮件的附件
		List<FileInfo> selectFileByMsg(Integer msgId);
		
		//删除邮件的附件
		void deleteFileByMsg(Integer msgId);
}
