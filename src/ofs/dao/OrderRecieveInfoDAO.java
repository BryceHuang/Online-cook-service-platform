package ofs.dao;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.OrderRecieveInfo;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRecieveInfoDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public int insertInfo(OrderRecieveInfo info) throws EbpException{
		int uid=0;
		try {
			uid=(Integer)hibernateTemplate.save(info);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new EbpException("添加订单接收信息的记录失败！");
		}
		return uid;
	}
	
	public void updateInfo(OrderRecieveInfo info) throws EbpException{
		
		try {
			hibernateTemplate.update(info);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new EbpException("添加订单接收信息的更新失败！");
		}
		
	}

}
