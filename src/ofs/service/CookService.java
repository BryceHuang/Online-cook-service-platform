package ofs.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ofs.dao.CookDAO;
import ofs.dao.OrderDAO;
import ofs.exception.EbpException;
import ofs.javabean.Cook;


import ofs.javabean.Order;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class CookService {
	@Resource
	private CookDAO cookDAO;
	@Resource
	private OrderDAO orderDAO;
	
	
	//厨师注册
	public Cook regist(String username,String password) throws EbpException{
		Cook cook=new Cook();
		Date now =new Date();
		cook.setCook_name(username);
		cook.setCook_password(password);
		cook.setReg_time(now);
		cook.setCook_money(0.0);
		cook.setIs_aduit(0);
		cook.setCook_status(1);
		cook.setCook_score(100.0);
		int uid=cookDAO.insertCook(cook);	
		return cook;
		
	}
	// 登陆查询
		public Cook getUser(String username) throws EbpException {
			return cookDAO.selectCook(username);
		}
	//厨师接单 ：返回1表示成功执行 返回0表示失败
		public int recieveOrder(Integer oid,Integer cookId) throws EbpException{
		    Order order =orderDAO.selectOrderById(oid);
		    Date now=new Date();
		    
			if(order.equals(null)||order.getOrder_status()!=1)
				return 0;
			if(order.equals(null)==false)
			{
				order.setOrder_status(2);
			    order.setCook_id(cookId);
			    order.setInsure_time(now);
				orderDAO.updateOrder(order);
				return 1;
			}
			return 1;
			
		}
	//厨师开始服务
		public int startServe(Integer oid,Integer cid) throws EbpException{
			Order order =orderDAO.selectOrderById(oid);
			Date now=new Date();
			if(order.equals(null))
				return 0;
			if(order.equals(null)==false){
				order.setOrder_status(3);
				order.setServe_sure_time(now);
				orderDAO.updateOrder(order);
				return 1;
			}
			return 1;
		}
		//厨师开始服务
	public int stopServe(Integer oid,Integer cid) throws EbpException{
		Order order =orderDAO.selectOrderById(oid);
		Date now=new Date();
		if(order.equals(null))
			return 0;
		if(order.equals(null)==false){
			order.setOrder_status(4);
			//order.setServe_sure_time(now);
			orderDAO.updateOrder(order);
			return 1;
		}
		return 1;
	}
		
    //厨师申请变更订单
		/**
		 * @return isApply
		 * 0表示不能变更 1表示变更成功
		 * 
		 * */
		public int changeOrder(Integer oid,Integer cid,String change_detail,String changeApplicant) throws EbpException{
			Order order =orderDAO.selectOrderById(oid);
			System.out.print("获取的order为："+order.toString());
			Date now=new Date();
			
			/*
			 * if(order.equals(null)||!order.getChange_applicant().equals(null))
				return 0;*/
			if(!order.equals(null)){
				order.setIs_change(0);
				order.setChange_detail(change_detail);
				order.setChange_applicant(changeApplicant);
				orderDAO.updateOrder(order);
				return 1;
			}
			return 1;
		}
		//厨师进行评分操作
		public int scoreOrderCook(Integer oid,Double score) throws EbpException{
			Order order =orderDAO.selectOrderById(oid);
			//if(order.equals(null)||!order.getOrder_score_ToUser().equals(null))
			if(order.equals(null))
				return 0;
			if(!order.equals(null)){
				order.setOrder_score_ToUser(score);				
				orderDAO.updateOrder(order);
				return 1;
			}
			return 1;
		}
		
		//厨师的审核操作
		public int aduitCook(Integer cid,Integer aduitStauts,Integer aduitPerson,String aduitDetail) throws EbpException{
			Cook cook=cookDAO.selectCookById(cid);
			if(cook.equals(null))
				return 0;
			if(!cook.equals(null)){
				cook.setIs_aduit(aduitStauts);
				cook.setAduit_person(aduitPerson);
				cook.setAduit_detail(aduitDetail);
				cookDAO.updateCook(cook);
				return 1;
			}
			return 1;
		}
		
		
		//厨师的申请审核操作
		public int applyAduit(Integer cid,Integer aduitStauts,String idNumber,String license,String healthId,String cookIdURL,String cookLicenseURL,String cookHealthURL) throws EbpException{
			Cook cook=cookDAO.selectCookById(cid);
			if(cook.equals(null))
				return 0;
			if(!cook.equals(null)){
				cook.setIs_aduit(aduitStauts);
				cook.setCook_idnumber(idNumber);
				cook.setCook_license(license);
				cook.setCook_healthid(healthId);
				cook.setCook_idurl(cookIdURL);
				cook.setCook_licenseurl(cookLicenseURL);
				cook.setCook_healthurl(cookHealthURL);
				cookDAO.updateCook(cook);
				return 1;
			}
			return 1;
		}
		
		public Cook getCookById(Integer cid)
		{
			return cookDAO.selectCookById(cid);
		}
		public int updateCook2(Cook cook) throws EbpException{
			cookDAO.updateCook(cook);
			return 1;
		}

		
		//更新厨师信息
		public Cook updateCook(Integer cid,String rName,String password,String location,Integer gender,Integer age,String tel,Integer workedTime,String desc,String area) throws EbpException{
			Cook cook=cookDAO.selectCookById(cid);
			if(cook.equals(null))
				return null;
			if(!cook.equals(null)){
				cook.setCook_rname(rName);
				cook.setCook_password(password);
				cook.setCook_location(location);
				cook.setCook_gender(gender);
				cook.setCook_age(age);
				cook.setCook_tel(tel);
				cook.setCook_workedtime(workedTime);
				cook.setCook_desc(desc);
				cook.setLocation_code(area);
				cookDAO.updateCook(cook);
				return cook;
			}
			return cook;
		}
		//按审核状态查询厨师信息
		public List<Cook> getCookByAduit(Integer isAduit){			
			return cookDAO.selectCookByAduit(isAduit);
			
		}
		
		public List<Cook> getAllCooks(){
			return cookDAO.selectAllCooks();
		}
		
}
