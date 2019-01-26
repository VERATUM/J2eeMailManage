package com.aerfa.serive.imp;

import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.aerfa.dao.MsgInfoDao;
import com.aerfa.dao.imp.MsgInfoDaoImpl;
import com.aerfa.entity.MsgInfo;
import com.aerfa.serive.FileInfoSerive;
import com.aerfa.serive.MsgInfoService;

public class MsgInfoServiceImpl implements MsgInfoService{

	private MsgInfoDao dao = new MsgInfoDaoImpl();
	private FileInfoSerive fileDao = new FileInfoSeriveImp();
	
	@Override
	public Integer saveNote(MsgInfo msgInfo) {
		Integer mid = msgInfo.getMsg_id();
		try {
			if(mid==null)
				//保存草稿，（保存文件在FileInfoService中进行）
				mid = dao.addMsg(msgInfo);
			else
				dao.updateMsg(msgInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mid;
	}

	@Override
	public void editNote(MsgInfo msgInfo) {
		throw new RuntimeException("ediNote功能未完成");
	}

	@Override
	public void removeNote(Integer id) {
		try {
			MsgInfo msg = dao.findById(id);
			if(msg.getMsg_atten()!=0) {
				dao.removeQYMsg(id);
			}else {
				//删除草稿
				dao.deleteMsg(id);
				//删除草稿附件
				fileDao.deleteFileByMsg(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void removeNoteBatch(int[] ids) {
		try {
			//删除草稿
			dao.deleteMsgBatch(ids);
			//删除草稿附件
			for(Integer id:ids) {
				if(id!=null)
					fileDao.deleteFileByMsg(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer sendMsg(MsgInfo msgInfo) {
		try {
			//保存草稿
			Integer id = msgInfo.getMsg_id();
			
			if(id==null) {
				id = dao.addMsg(msgInfo);
			}else {
				dao.updateMsg(msgInfo);
			}
			
			//发送（更新发送标识）
			dao.setEmailType(id, MsgInfo.EMAIL);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void deleteMsg(Integer id) {
		removeNote(id);
	}

	@Override
	public void deleteMsgBatch(int[] ids) {
		removeNoteBatch(ids);
	}

	@Override
	public void removeMsg(Integer id) {
		if(id==null)
			return;
		try {
			dao.setRemoveState(id, MsgInfo.DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeMsgBatch(int[] ids) {
		if(ids==null)
			return;
		for(Integer id:ids) {
			removeMsg(id);
		}
	}

	@Override
	public void recoverMsg(Integer id) {
		if(id==null)
			return;
		try {
			dao.setRemoveState(id, MsgInfo.NO_DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void recoverMsgBatch(int[] ids) {
		if(ids==null)
			return;
		for(Integer id:ids) {
			recoverMsg(id);
		}
	}

	@Override
	public void readMsg(Integer id) {
		try {
			dao.setEmailState(id,MsgInfo.READED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void starMsg(Integer id) {
		try {
			dao.setEmailState(id, MsgInfo.STAR);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unStarMsg(Integer id) {
		try {
			dao.setEmailState(id, MsgInfo.NO_STAR);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MsgInfo findById(Integer id) {
		MsgInfo msg = null;
		try {
			msg = dao.selectById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public List<MsgInfo> findByEidNote(Integer eid) {
		try {
			return dao.findByNoteOrMsg(eid, MsgInfo.NOTE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidSendMsg(Integer eid) {
		try {
			return dao.findByNoteOrMsg(eid, MsgInfo.EMAIL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidNote(Integer eid) {
		try {
			return dao.countByNoteOrMsg(eid,MsgInfo.NOTE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidSendMsg(Integer eid) {
		try {
			return dao.countByNoteOrMsg(eid,MsgInfo.EMAIL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidAllMsg(String email) {
		try {
			return dao.findByMsgAll(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidAllMsg(String email) {
		try {
			return dao.countByMsgAll(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidNewMsg(String email) {
		try {
			return dao.findByMsgState(email, MsgInfo.NO_READED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidNewMsg(String email) {
		try {
			return dao.countByMsgState(email, MsgInfo.NO_READED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidOldMsg(String email) {
		try {
			return dao.findByMsgState(email, MsgInfo.READED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidOldMsg(String email) {
		try {
			return dao.countByMsgState(email, MsgInfo.READED);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidRubbishMsg(String email) {
		try {
			return dao.findByMsgDelete(email, MsgInfo.DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidRubbishMsg(String email) {
		try {
			return dao.countByMsgDelete(email, MsgInfo.DELETE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MsgInfo> findByEidStarMsg(String email) {
		try {
			return dao.findByMsgState(email, MsgInfo.STAR);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByEidStarMsg(String email) {
		try {
			return dao.countByMsgState(email, MsgInfo.STAR);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deletemore(Integer emp_id) {
		return dao.deletemore(emp_id);
	}

	@Override
	public List<MsgInfo> findByQYMsg(Integer did) {
		try {
			return dao.findByQYMsg(did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer countByQYMsg(Integer did) {
		try {
			return dao.countByQYMsg(did);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deletemore(String emp_id) {
		return this.dao.deletemore(emp_id);
	}

	public int updataMail(String emp_email, String a_emp_email) {
		return this.dao.updataMail(emp_email, a_emp_email);
	}

}
