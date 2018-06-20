package ofs.dao;

import javax.annotation.Resource;

import ofs.javabean.UserLoginLog;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginLogDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public void insertLog(UserLoginLog userLoginLog)
	{
		hibernateTemplate.save(userLoginLog);
	}
}
