package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.Log;

public interface LogSerive {
	    //添加
		public int addLog(Log log);
		//删除
		public int deleteLog(Integer logid);
		//修改
		public int updateLog(Log log);
		//查所有
		public List<Log> selectLog();
		//查单条
		public Log selecOneLog(Integer logid);
}
