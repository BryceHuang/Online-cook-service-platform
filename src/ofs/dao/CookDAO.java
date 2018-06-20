package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.Cook;
import ofs.javabean.User;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CookDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_USER = "from Cook where cook_name=?";
	private static final String SELECT_ALL_COOK = "from Cook";
	private static final String SELECT_ADUIT_COOK="from Cook where is_aduit=?";
	private static final String SELECT_COOK="from Cook where cook_id=?";
	
	public Cook selectCook(String username) throws EbpException
	{
		List<Cook> cooks = hibernateTemplate.find(SELECT_USER, new Object[] {
				username });

		// 
		if (cooks.isEmpty()) {
			throw new EbpException("没有该厨师");
		} else {
			if(cooks.get(0).getCook_status()==0)
				throw new EbpException("该厨师已经被禁用");
			else
				return cooks.get(0);
		}
	}
	
	public Cook selectCookById(Integer cid){
		List<Cook> cooks =hibernateTemplate.find(SELECT_COOK,new Object[]{cid});
		Cook cook=cooks.get(0);
		return cook;
	}
	//获取所有的厨师信息
	public List<Cook> selectAllCooks(){
		return hibernateTemplate.find(SELECT_ALL_COOK);
		
	}
	
	public List<Cook> selectCookByAduit(Integer aduit){
		return hibernateTemplate.find(SELECT_ADUIT_COOK,new Object[]{aduit});
	}
	//更新厨师信息
	public void updateCook(Cook cook)throws EbpException{
		try {
			hibernateTemplate.update(cook);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new EbpException("更改厨师信息失败");
		}
	}
	//插入厨师信息
	public int insertCook(Cook cook) throws EbpException{
		int uid=0;
		try {
			uid=(Integer)hibernateTemplate.save(cook);
		} catch (DataAccessException e) {
			
			e.printStackTrace();
			throw new EbpException("厨师注册失败！");
		}
		return uid;
	}
	

}
