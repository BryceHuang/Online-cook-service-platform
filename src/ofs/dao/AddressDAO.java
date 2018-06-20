package ofs.dao;

import java.util.List;

import javax.annotation.Resource;

import ofs.javabean.Area;
import ofs.javabean.City;
import ofs.javabean.Province;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDAO {
	@Resource
	private HibernateTemplate hibernateTemplate;
	private static final String SELECT_PROVINCE = "from Province";
	private static final String SELECT_CITY = "from City where province_code =?";
	private static final String SELECT_ALL_CITY = "from City";
	private static final String SELECT_AREA = "from Area where city_code=?";
	private static final String SELECT_ALL_AREA = "from Area";
	
	public List<Province> selectProvince(){
		return hibernateTemplate.find(SELECT_PROVINCE);
	}
	public List<City> selectCity(String provinceCode){
		return hibernateTemplate.find(SELECT_CITY,new Object[]{provinceCode});
	}
	public List<City> selectAllCity(){
		return hibernateTemplate.find(SELECT_ALL_CITY);
	}
	public List<Area> selectArea(String cityCode){
		return hibernateTemplate.find(SELECT_AREA,new Object[]{cityCode});
	}
	public List<Area> selectAllArea(){
		return hibernateTemplate.find(SELECT_ALL_AREA);
	}
}
