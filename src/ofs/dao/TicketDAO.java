package ofs.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.Ticket;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TicketDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_ALL_TICKET = "from Ticket";
	private static final String SELECT_TIME_TICKET = "from Ticket where startTime between ? and ?";
	private static final String SELECT_PIC_TICKET="from Ticket where tid=?";
	
	
	
	//��ѯ����Ʊ
	@SuppressWarnings("unchecked")
	public List<Ticket> selectAllTicket(final int maxLines , final int page) {
		List<Ticket> tickets = hibernateTemplate.executeFind(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(SELECT_ALL_TICKET);
				query.setFirstResult(maxLines * page - maxLines);
				query.setMaxResults(maxLines);
				return query.list();
			}
		});
		return tickets;
	}
	
	public String FindImg(int uid){
		String imgurl="";
		List<Ticket> tickets=hibernateTemplate.find(SELECT_PIC_TICKET,new Object[]{uid});
		if(tickets!=null&&tickets.size()>0){
			imgurl=tickets.get(0).getPicurl();
		}
		return imgurl;
	}
	
	
	//��ѯ���м�¼����
	public int selectTicketCount() {
		return hibernateTemplate.find(SELECT_ALL_TICKET).size();
	}
	
	//����ʼ���ںͽ������ڲ�ѯ
	@SuppressWarnings("unchecked")
	public List<Ticket> selectTicketByTime(final int maxLines , final int page , final Date begin , final Date end) {
		List<Ticket> tickets = hibernateTemplate.executeFind(new HibernateCallback<Object>() {

			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(SELECT_TIME_TICKET);
				query.setDate(0, begin);
				query.setDate(1, end);
				query.setFirstResult(maxLines * page - maxLines);
				query.setMaxResults(maxLines);
				return query.list();
			}
		});
		return tickets;
	}
	
	//��ѯ��ʼ���ڵ��������ڵļ�¼����
	public int selectTimeTicketCount(Date begin , Date end) {
		return hibernateTemplate.find(SELECT_TIME_TICKET , new Object[]{begin , end}).size();
	}
	
	//���Ʊ��
	public void insertTicket(Ticket ticket) throws EbpException {
		try{
			System.out.print("PPPPPPPPPPPPPPPPPP>>>>>>>>>>>"+ticket.getPicurl());
			hibernateTemplate.save(ticket);
		}catch(Exception e) {
			throw new EbpException("���Ʊ��ʧ��");
		}
	}
	
	//�޸�Ʊ��״̬
	public void updateTicketStatus(Ticket ticket) throws EbpException {
		try {
			hibernateTemplate.update(ticket);
		} catch (Exception e) {
			throw new EbpException("�޸�Ʊ��״̬ʧ��");
		}
	}
	
	//��tid��ѯƱ��
	public Ticket selectTicketById(int tid) {
		return hibernateTemplate.get(Ticket.class, tid);
	}
	
	//�޸�Ʊ��
	public void updateTicket(Ticket ticket) throws EbpException {
		try {
			hibernateTemplate.update(ticket);
		} catch (Exception e) {
			throw new EbpException("�޸�Ʊ��ʧ��");
		}
	}
	
}
