package ofs.service;

import java.util.List;

import javax.annotation.Resource;

import ofs.dao.AdminUserDAO;
import ofs.exception.EbpException;
import ofs.javabean.AdminUser;

import org.springframework.stereotype.Service;


@Service
public class AdminUserService {
	@Resource
	private AdminUserDAO adminUserDAO;
	
	//根据用户名和密码获取用户信息
	public AdminUser getUser(String username , String password) throws EbpException {
		return adminUserDAO.selectUser(username , password);
	}
	//根据用户名和密码获取用户信息
	public AdminUser getUser2(String username , String password) throws EbpException {
		return adminUserDAO.selectServer(username , password);
	}
	//获取所有的adminer数据
	public List<AdminUser> getAdminers(){
		return adminUserDAO.selectAllUsers();
	}
	
	public List<AdminUser> getAdminByGroup(Integer group){
		return adminUserDAO.selectUserByGroup(group);
	}
	
	public AdminUser getAdminById(Integer id){
		return adminUserDAO.selectUserById(id);
	}
	public void updateAdminUser(AdminUser adminUser) throws EbpException{
		adminUserDAO.updateUser(adminUser);
	}
}
