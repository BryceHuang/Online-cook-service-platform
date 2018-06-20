package ofs.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import ofs.dao.UserTicketDAO;
import ofs.javabean.Ticket;

import org.springframework.stereotype.Service;



@Service
public class UserTicketService {
	@Resource
	private UserTicketDAO userTicketDAO;
	
	//按日期查询票项
	public List<Ticket> getDateTickets(Date begin,Date end) {
		
		return userTicketDAO.selectDateTicket(begin, end);
	}
}

