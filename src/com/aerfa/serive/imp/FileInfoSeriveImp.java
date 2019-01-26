package com.aerfa.serive.imp;

import java.util.List;

import com.aerfa.dao.FileInfoDao;
import com.aerfa.dao.imp.FileInfoDaoImp;
import com.aerfa.entity.FileInfo;
import com.aerfa.serive.FileInfoSerive;

public class FileInfoSeriveImp implements FileInfoSerive {
	private FileInfoDao fileInfoDaoImp = new FileInfoDaoImp();
	@Override
	/**
	 * 添加附件
	 * FileInfo 为附件实体
	 * 返回影响行数
	 * */
	public int addFileInfo(FileInfo fileInfo) {
		return fileInfoDaoImp.addFileInfo(fileInfo);
	}

	@Override
	/**
	 * 删除附件
	 * FileInfoId 为附件 主键
	 * 返回影响行数
	 * */
	public int deleteFileInfo(Integer fileInfoid) {
		
		return fileInfoDaoImp.deleteFileInfo(fileInfoid);
	}

	@Override
	/**
	*修改附件信息
	*fileInfo为实体 通过实体主键修改
	*返回影响行数
	*/
	public int updateFileInfo(FileInfo fileInfo) {
		
		return fileInfoDaoImp.updateFileInfo(fileInfo);
	}

	@Override
	/**
	*查询所有附件
	*返回附件集合
	*/
	public List<FileInfo> selectFileInfo() {
		
		return fileInfoDaoImp.selectFileInfo();
	}

	@Override
	/**
	 * 查询单个附件
	 *fileInfoid为附件主键
	 *返回附件实体 
	 */
	public FileInfo selecOneFileInfo(Integer fileInfoid) {
		
		return fileInfoDaoImp.selecOneFileInfo(fileInfoid);
	}

	@Override
	public List<FileInfo> selectFileByMsg(Integer msgId) {
		List<FileInfo> fileList = fileInfoDaoImp.selectFileByMsg(msgId);
		return fileList;
	}

	@Override
	public void deleteFileByMsg(Integer msgId) {
		fileInfoDaoImp.deleteFileByMsg(msgId);
	}
	
}
