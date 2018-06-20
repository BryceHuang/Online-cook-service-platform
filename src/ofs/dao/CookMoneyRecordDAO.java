package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.SysCookMoneyRecord;
import ofs.javabean.SysUserMoneyRecord;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class CookMoneyRecordDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_Cook_Record = "from SysCookMoneyRecord where cook_id=? order by operate_time desc";
	
	//添加记录
	public int insertRecord(SysCookMoneyRecord record) throws EbpException{
		int uid=0;
		try{
		uid=(Integer)hibernateTemplate.save(record);
		}catch (Exception e) {
			throw new EbpException("添加厨师资金记录失败");
		}
		return uid;
		}
	public List<SysCookMoneyRecord> getRecord(Integer cid){
		return hibernateTemplate.find(SELECT_Cook_Record, new Object[]{cid});
	}
}
