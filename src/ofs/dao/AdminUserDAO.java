package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.AdminUser;
import ofs.javabean.Cook;
import ofs.javabean.User;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AdminUserDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_USER = "from AdminUser where Admin_Name=? and Admin_Password=? and admin_level=1";
	private static final String SELECT_SERVER = "from AdminUser where Admin_Name=? and Admin_Password=? and admin_level>1 ";
	private static final String SELECT_ALL_USER = "from AdminUser ";
	private static final String SELECT_USER_BY_GROUP="from AdminUser where admin_group=? ";
	private static final String SELECT_USER_BY_ID = "from AdminUser where admin_id=?";
	//查找用户
	public AdminUser selectUser(String username , String password) throws EbpException {
		List<AdminUser> users = hibernateTemplate.find(SELECT_USER , new Object[]{username , password});
		//
		if(users.isEmpty()) {
			throw new EbpException("登陆失败！");
		}
		else {
			return users.get(0);
		}
	}
	//查找用户
	public AdminUser selectServer(String username , String password) throws EbpException {
			List<AdminUser> users = hibernateTemplate.find(SELECT_SERVER , new Object[]{username , password});
			//
			if(users.isEmpty()) {
				throw new EbpException("登陆失败！");
			}
			else {
				return users.get(0);
			}
		}
	
	//获取所有用户信息
	public List<AdminUser> selectAllUsers(){
		return hibernateTemplate.find(SELECT_ALL_USER);
	}
	
	//获取特定分组的用户
	public List<AdminUser> selectUserByGroup(Integer group){
		return hibernateTemplate.find(SELECT_USER_BY_GROUP,new Object[]{group});
	}
	//按照id查找用户
	public AdminUser selectUserById(Integer id){
		List<AdminUser> admins= hibernateTemplate.find(SELECT_USER_BY_ID,new Object[]{id});
		return admins.get(0);
	}
	//更新管理员信息
		public void updateUser(AdminUser adminUser)throws EbpException{
			try {
				hibernateTemplate.update(adminUser);
			} catch (DataAccessException e) {				
				e.printStackTrace();
				throw new EbpException("更改管理员信息失败");
			}
		}
		//删除厨师信息
		public void deleteCook(Cook cook)throws EbpException{
			try {
				hibernateTemplate.delete(cook);
			} catch (DataAccessException e) {
				
				e.printStackTrace();
				throw new EbpException("删除厨师信息失败");
			}
		}
		//删除用户信息
		public void deleteUser(User user)throws EbpException{
			try {
				hibernateTemplate.delete(user);
			} catch (DataAccessException e) {
				
				e.printStackTrace();
				throw new EbpException("删除用户信息失败");
			}
		}
		//删除客服信息
		public void deleteServer(AdminUser server)throws EbpException{
			try {
				hibernateTemplate.delete(server);
			} catch (DataAccessException e) {
				
				e.printStackTrace();
				throw new EbpException("删除客服信息失败");
			}
		}
	
}

