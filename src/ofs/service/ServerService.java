package ofs.service;

import javax.annotation.Resource;

import ofs.dao.AdminUserDAO;
import ofs.dao.CookDAO;
import ofs.dao.OrderDAO;
import ofs.exception.EbpException;
import ofs.javabean.Cook;
import ofs.javabean.Order;

import org.springframework.stereotype.Service;

@Service
public class ServerService {
	@Resource
	private AdminUserDAO adminUserDAO;
	@Resource
	private CookDAO cookDAO;
	@Resource
	private OrderDAO orderDAO;

	
	
	//审核厨师信息的操作
	public void aduitCook(Integer sid,Integer cid,Integer aduitStatus,String aduitDetail) throws EbpException{
		Cook cook=cookDAO.selectCookById(cid);
		cook.setAduit_person(sid);
		cook.setIs_aduit(aduitStatus);
		cook.setAduit_detail(aduitDetail);
		cookDAO.updateCook(cook);				
	}
	
	//受理变更订单的操作
	public void changeOrder(Integer oid,Integer sid,Integer isChange,String changeDetail) throws EbpException{
		Order order=orderDAO.selectOrderById(oid);
	    String oChangeDetail=order.getChange_detail();
		order.setChange_person(sid);
		order.setIs_change(isChange);
		if(isChange==1)
		{
			order.setOrder_status(1);
			
		}

		order.setChange_detail(oChangeDetail+";server"+String.valueOf(sid)+changeDetail);
		orderDAO.updateOrder(order);
		
	}
	
	

}
