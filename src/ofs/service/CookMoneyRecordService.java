package ofs.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ofs.dao.CookMoneyRecordDAO;
import ofs.dao.UserMoneyRecordDAO;
import ofs.exception.EbpException;
import ofs.javabean.SysCookMoneyRecord;
import ofs.javabean.SysUserMoneyRecord;
@Service
public class CookMoneyRecordService {
	@Resource
	private CookMoneyRecordDAO moneyDAO;
	//添加记录 厨师只有充值和提现两种记录
	public int insertRecord(Integer uid,Integer operateKind,Double originalMoney,Double operateMoney,String bankKind,String cardId){
		SysCookMoneyRecord record=new SysCookMoneyRecord();
		Date now=new Date();
		Double leftMoney;
		if(operateKind==1) //1:表示充值 服务费的充值
			leftMoney=originalMoney+operateMoney;
		else if(operateKind==2) //2：表示提现
		    leftMoney=originalMoney-operateMoney; //只有当操作类型为支付，并且支付方式为余额时，剩余的钱才会减少
		else 
			leftMoney=originalMoney;		
		record.setUser_id(uid);
		record.setOriginal_money(originalMoney);
		record.setOperate_money(operateMoney);
		record.setLeft_money(leftMoney);
		record.setOperate_kind(operateKind);
		record.setBank_kind(bankKind);
		record.setCard_id(cardId);
		record.setOperate_time(now);
		try {
			moneyDAO.insertRecord(record);
		} catch (EbpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public List<SysCookMoneyRecord> getCookMoneyRecord(Integer cid){
		return moneyDAO.getRecord(cid);
	}
}
