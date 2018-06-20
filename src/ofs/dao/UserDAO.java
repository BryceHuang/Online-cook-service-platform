package ofs.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import ofs.exception.EbpException;
import ofs.javabean.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;




@Repository
public class UserDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_USER = "from User where user_name=?";
	private static final String SELECT_USER_ID="from User where user_id=?";
	private static final String SELECT_ALL_USER = "from User";
	private static final String SELECT_TIME_USER = "from User where reg_Time between ? and ?";

	// 登陆
	public User selectUser(String username)
			throws EbpException {
		List<User> users = hibernateTemplate.find(SELECT_USER, new Object[] {
				username });

		// 
		if (users.isEmpty()) {
			throw new EbpException("用户名或密码错误！");
		} else {
			if(users.get(0).getUser_status()==0)
				throw new EbpException("该用户已被禁用");
			else
				return users.get(0);
		}
	}

	// 注册
	public int insertUser(User user) throws EbpException {
		int uid = 0;
		try {
			uid = (Integer) hibernateTemplate.save(user);
		} catch (Exception e) {
			throw new EbpException("注册失败");
		}
		return uid;
	}
	

	// 更新
	public void updateUser(User user) throws EbpException {
		try {
			hibernateTemplate.update(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new EbpException("更改信息失败");
		}
	}
	public User selectUserByID(Integer uid){
		
		List<User> users= hibernateTemplate.find(SELECT_USER_ID,new Object[]{uid});
		User user=users.get(0);
		return user;
	}

	// ��id��ѯ
	public User selectUserById(String string) {
		return hibernateTemplate.get(User.class, string);
	}

	// ��ѯ�����û�
	@SuppressWarnings("unchecked")
	public List<User> selectAllUser(final int maxLines, final int page) {
		List<User> users = hibernateTemplate
				.executeFind(new HibernateCallback<Object>() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(SELECT_ALL_USER);
						query.setFirstResult(maxLines * page - maxLines);
						query.setMaxResults(maxLines);
						return query.list();
					}
				});
		return users;
	}
	
	//获取所有的用户信息
	@SuppressWarnings("unchecked")
	public List<User> selectAllUsers() {
		List<User> users = hibernateTemplate.find(SELECT_ALL_USER);
		return users;
	}

	// ��ѯ���м�¼����
	public int selectOrderCount() {
		return hibernateTemplate.find(SELECT_ALL_USER).size();
	}

	// ����ʼ���ںͽ������ڲ�ѯ�û�
	@SuppressWarnings("unchecked")
	public List<User> selectUserByTime(final int maxLines, final int page,
			final Date begin, final Date end) {
		List<User> users = hibernateTemplate
				.executeFind(new HibernateCallback<Object>() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(SELECT_TIME_USER);
						query.setDate(0, begin);
						query.setDate(1, end);
						query.setFirstResult(maxLines * page - maxLines);
						query.setMaxResults(maxLines);
						return query.list();
					}
				});
		return users;
	}

	// ��ѯ��ʼ���ڵ��������ڵļ�¼����
	public int selectTimeUserCount(Date begin, Date end) {
		return hibernateTemplate.find(SELECT_TIME_USER,
				new Object[] { begin, end }).size();
	}	

	// ��������ϲ�ѯ
	@SuppressWarnings("unchecked")
	public List<User> selectConditionUser(final int maxLines,
			final int page, final String name,
			final String idCard , final String telno) {
		String hql = SELECT_ALL_USER;
		if (!name.equals("") || !idCard.equals("") || !telno.equals("")) {
			hql += " where ";
		}
		if (!name.equals("")) {
			hql += "name=?";
		}
		if (!idCard.equals("")) {
			if (!name.equals("")) {
				hql += " and ";
			}
			hql += "idCard=?";
		}
		if (!telno.equals("")) {
			if (!name.equals("") || !idCard.equals("")) {
				hql += " and ";
			}
			hql += "telno=?";
		}

		final String conditionHql = hql;
		List<User> users = hibernateTemplate
				.executeFind(new HibernateCallback<Object>() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {

						Query query = session.createQuery(conditionHql);
						if (!name.equals("")) {
							query.setString(0, name);
						}
						if (!idCard.equals("")) {
							if (name.equals("")) {
								query.setString(0, idCard);
							} else {
								query.setString(0, name);
								query.setString(1, idCard);
							}
						}
						if (!telno.equals("")) {
							if (name.equals("") && idCard.equals("")) {
								query.setString(0, telno);
							} else if (!name.equals("") && idCard.equals("")) {
								query.setString(0, name);
								query.setString(1, telno);
							} else if (name.equals("") && !idCard.equals("")) {
								query.setString(0, idCard);
								query.setString(1, telno);
							} else if (!name.equals("") && !idCard.equals("")) {
								query.setString(0, name);
								query.setString(1, idCard);
								query.setString(2, telno);
							}
						}
						query.setFirstResult(maxLines * page - maxLines);
						query.setMaxResults(maxLines);
						return query.list();
					}
				});

		return users;
	}

	// ��������ϲ�ѯ�ļ�¼����
	public int conditionUserCount(String name, String idCard , String telno) {

		String hql = SELECT_ALL_USER;
		if (!name.equals("") || !idCard.equals("") || !telno.equals("")) {
			hql += " where ";
		}
		Object[] args = null;
		if (!name.equals("")) {
			hql += "name=?";
			args = new Object[] { name };
		}
		if (!idCard.equals("")) {
			if (!name.equals("")) {
				hql += " and ";
				args = new Object[] { name ,idCard };
			} else {
				args = new Object[] { name };
			}
			hql += "idCard=?";

		}
		if (!telno.equals("")) {
			if (!name.equals("") || !idCard.equals("")) {
				hql += " and ";
			}
			if (name.equals("") && idCard.equals("")) {
				args = new Object[] { telno };
			} else if (name.equals("") && !idCard.equals("")) {
				args = new Object[] { idCard ,telno };
			} else if (!name.equals("") && idCard.equals("")) {
				args = new Object[] { name, telno };
			} else if (!name.equals("") && !idCard.equals("")) {
				args = new Object[] { name, idCard, telno };
			}
			hql += "telno=?";
		}
		System.out.println(hql);
		if (args == null) {
			return hibernateTemplate.find(hql).size();
		} else {
			return hibernateTemplate.find(hql, args).size();
		}

	}
}
