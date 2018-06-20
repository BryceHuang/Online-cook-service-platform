package ofs.dao;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import ofs.javabean.Ticket;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;



@Repository
public class UserTicketDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_DATE = "from Ticket where startTime between ? and ?";
	
	//查询7日内的票项
	public List<Ticket> selectDateTicket(Date begin , Date end) {
		return hibernateTemplate.find(SELECT_DATE , new Object[]{begin , end});
	}
}
