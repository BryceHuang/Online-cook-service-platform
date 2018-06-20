package ofs.service;

import java.util.Date;

import javax.annotation.Resource;

import ofs.dao.OrderRecieveInfoDAO;
import ofs.javabean.OrderRecieveInfo;

import org.springframework.stereotype.Service;

@Service
public class OrderRecieveInfoService {
	@Resource
	private OrderRecieveInfoDAO infoDAO;
	public int insertInfo(Integer uid,Integer oid,Integer cid){
		OrderRecieveInfo info=new OrderRecieveInfo();
		Date now =new Date();
		info.setUser_id(uid);
		info.setOrder_id(oid);
		info.setOrder_status(1);
		info.setCook_id(cid);
		info.setUpdate_time(now);
		try {
			infoDAO.insertInfo(info);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
			
		}
		
		
	}
	
	
}
