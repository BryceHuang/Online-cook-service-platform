package ofs.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ofs.dao.UserMoneyRecordDAO;
import ofs.exception.EbpException;
import ofs.javabean.SysUserMoneyRecord;

import org.springframework.stereotype.Service;

@Service
public class UserMoneyRecordService {
	@Resource
	private UserMoneyRecordDAO moneyDAO;
	
	//添加记录
	public int insertRecord(Integer uid,Integer operateKind,Double originalMoney,Double operateMoney,String bankKind,String cardId,Integer payKind){
		SysUserMoneyRecord record=new SysUserMoneyRecord();
		//System.out.print("提现操作的类型应该是2          ------"+operateKind);
		Date now=new Date();
		Double leftMoney;
		if(operateKind==1) //1:表示充值
			leftMoney=originalMoney+operateMoney;
		else if(operateKind==2||operateKind==3&&payKind==1) 
		    leftMoney=originalMoney-operateMoney; //只有当操作类型为支付，并且支付方式为余额时，剩余的钱才会减少
		else 
			leftMoney=originalMoney;
		
		
		record.setUser_id(uid);
		record.setOriginal_money(originalMoney);
		record.setOperate_money(operateMoney);
		record.setLeft_money(leftMoney);
		record.setOperate_kind(operateKind);
		//System.out.print("提现操作的类型应该是22222222           ------"+operateKind);
		record.setBank_kind(bankKind);
		record.setCard_id(cardId);
		record.setOperate_time(now);
		record.setPay_kind(payKind);
		try {
			moneyDAO.insertRecord(record);
		} catch (EbpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public List<SysUserMoneyRecord> getMyRecord(Integer uid){
		return moneyDAO.getRecord(uid);
		
	}

}
