package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.SysUserMoneyRecord;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserMoneyRecordDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	
	private static final String SELECT_USER_Record = "from SysUserMoneyRecord where user_id=? order by operate_time desc";
	
	
	//添加记录
	public int insertRecord(SysUserMoneyRecord record) throws EbpException{
		int uid=0;
		try{
		uid=(Integer)hibernateTemplate.save(record);
		}catch (Exception e) {
			throw new EbpException("添加用户资金记录失败");
		}
		return uid;
		}

	public List<SysUserMoneyRecord> getRecord(Integer uid){
		return hibernateTemplate.find(SELECT_USER_Record, new Object[]{uid});
	}
}
