package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.aerfa.dao.FileInfoDao;
import com.aerfa.dao.LogDao;
import com.aerfa.entity.FileInfo;
import com.aerfa.entity.Log;
import com.aerfa.utils.JDBCUntils;

public class FileInfoDaoImp implements FileInfoDao {

	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	@Override
	/**
	 * 添加附件
	 * FileInfo 为附件实体
	 * 返回影响行数
	 * */
	public int addFileInfo(FileInfo fileInfo) {
		String sql = "INSERT INTO fileinfo (file_name,file_type,file,msg_id) VALUES(?,?,?,?);";
		int tmep = 0;
		try {
			tmep = qr.update(sql, fileInfo.getFile_name(),fileInfo.getFile_type(),fileInfo.getFile(),fileInfo.getMsg_id());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tmep;
	}

	@Override
	/**
	 * 删除附件
	 * FileInfoId 为附件 主键
	 * 返回影响行数
	 * */
	public int deleteFileInfo(Integer fileInfoid) {
		int tmep = 0;
		String sql = "DELETE FROM fileinfo WHERE file_id=?;";
		try {
			tmep = qr.update(sql,fileInfoid );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tmep;
	}

	@Override
	/**
	*修改附件信息
	*fileInfo为实体 通过实体主键修改
	*返回影响行数
	*/
	public int updateFileInfo(FileInfo fileInfo) {
		int tmpe = 0 ;
		String sql = "UPDATE fileinfo SET file_name =?,file_type=?,FILE=?,msg_id=? WHERE file_id=?";
		try {
			tmpe = qr.update(sql, fileInfo.getFile_name(),fileInfo.getFile_type(),fileInfo.getFile(),fileInfo.getMsg_id(),fileInfo.getFile_id());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tmpe;
	}

	@Override
	/**
	*查询所有附件
	*返回附件集合
	*/
	public List<FileInfo> selectFileInfo() {
		List<FileInfo> list=null;
		String sql = "SELECT * FROM fileinfo";
		try {
			list = qr.query(sql, new BeanListHandler<>(FileInfo.class));
            		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	/**
	 * 查询单个附件
	 *fileInfoid为附件主键
	 *返回附件实体 
	 */
	public FileInfo selecOneFileInfo(Integer fileInfoid) {
		FileInfo f = null;
		String sql = "SELECT * FROM fileinfo WHERE file_id=? ;";
		try {
			f = qr.query(sql,new BeanHandler<>(FileInfo.class),fileInfoid );
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<FileInfo> selectFileByMsg(Integer msgId) {
		String sql = "SELECT * FROM fileinfo WHERE msg_id=?";
		try {
			List<FileInfo> list = qr.query(sql,new BeanListHandler<>(FileInfo.class),msgId );
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//删除邮件的附件
	@Override
	public void deleteFileByMsg(Integer msgId) {
		String sql = "delete FROM fileinfo WHERE msg_id=?";
		try {
			qr.update(sql,msgId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	

}
