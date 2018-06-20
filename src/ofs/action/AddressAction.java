package ofs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ofs.dao.AddressDAO;
import ofs.dao.OrderDaoJDBC;
import ofs.javabean.Area;
import ofs.javabean.City;
import ofs.javabean.Province;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;
import org.json.JSONArray;
import org.json.JSONObject;
@Controller
@Namespace("/publicAction")
@ParentPackage("struts-default")
public class AddressAction {
	@Resource
	private AddressDAO addressDAO;
	//获取省份
		@Action(value = "getProvinceAction")
		public void getProvince() throws IOException{
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			List<Province> provinceList=addressDAO.selectProvince();
			JSONArray provinceArray=new JSONArray();
			for(int i=0;i<provinceList.size();i++)
			{
				JSONObject provinceJ=new JSONObject();
				provinceJ.put("provinceCode", provinceList.get(i).getProvince_code());
				provinceJ.put("provinceName", provinceList.get(i).getProvince_name());
				provinceArray.put(provinceJ);
			}
			PrintWriter pw = response.getWriter();
			pw.write(provinceArray.toString());
	        pw.flush();
	        pw.close();
		}
		
		
		@Action(value = "getCityAction")
		public void getCity() throws IOException{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String type=request.getParameter("type");
		
			String provinceCode=request.getParameter("provinceCode");
			List<City> cityList=new ArrayList<City>();
			if(type.equals("0"))
				cityList=addressDAO.selectCity(provinceCode);
			else if(type.equals("1"))
				cityList=addressDAO.selectAllCity();
			JSONArray cityArray=new JSONArray();
			for(int i=0;i<cityList.size();i++)
			{
				JSONObject cityJ=new JSONObject();
				cityJ.put("cityCode", cityList.get(i).getCity_code());
				cityJ.put("cityName", cityList.get(i).getCity_name());
				cityArray.put(cityJ);
			}
			PrintWriter pw = response.getWriter();
			pw.write(cityArray.toString());
	        pw.flush();
	        pw.close();
		}
		
		@Action(value = "getAreaAction")
		public void getArea() throws IOException{
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			String type=request.getParameter("type");		
			String cityCode=request.getParameter("cityCode");
			List<Area> areaList=new ArrayList<Area>();
			if(type.equals("0"))
				areaList=addressDAO.selectArea(cityCode);
			else if(type.equals("1"))
				areaList=addressDAO.selectAllArea();
			JSONArray areaArray=new JSONArray();
			for(int i=0;i<areaList.size();i++)
			{
				JSONObject areaJ=new JSONObject();
				areaJ.put("areaCode", areaList.get(i).getArea_code());
				areaJ.put("areaName", areaList.get(i).getArea_name());
				areaArray.put(areaJ);
			}
			PrintWriter pw = response.getWriter();
			pw.write(areaArray.toString());
	        pw.flush();
	        pw.close();
			
		}
		
		

		
		
			
	
}
