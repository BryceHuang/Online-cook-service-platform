package ofs.exception;

import ofs.dao.OrderDaoJDBC;

import org.json.JSONArray;
import org.json.JSONObject;

public class HelloB {
	public static void main(String[] args) {
		JSONArray countryOrderCount=new JSONArray();
		OrderDaoJDBC orderCount=new OrderDaoJDBC();		
		JSONObject orderCount1=new JSONObject();
		JSONObject orderCount2=new JSONObject();
		JSONObject orderCount3=new JSONObject();
		JSONObject orderCount4=new JSONObject();
		JSONObject orderCount5=new JSONObject();
		JSONObject orderCount6=new JSONObject();
		JSONObject orderCount7=new JSONObject();
		JSONObject orderCount8=new JSONObject();		
		orderCount1.put("value", orderCount.getOrderCuisineCountSystem("川菜"));
		orderCount1.put("name", "川菜");
		orderCount2.put("value", orderCount.getOrderCuisineCountSystem("鲁菜"));
		orderCount2.put("name", "鲁菜");
		orderCount3.put("value", orderCount.getOrderCuisineCountSystem("粤菜"));
		orderCount3.put("name", "粤菜");
		orderCount4.put("value", orderCount.getOrderCuisineCountSystem("苏菜"));
		orderCount4.put("name", "苏菜");
		orderCount5.put("value", orderCount.getOrderCuisineCountSystem("浙菜"));
		orderCount5.put("name", "浙菜");
		orderCount6.put("value", orderCount.getOrderCuisineCountSystem("闽菜"));
		orderCount6.put("name", "闽菜");
		orderCount7.put("value", orderCount.getOrderCuisineCountSystem("湘菜"));
		orderCount7.put("name", "湘菜");
		orderCount8.put("value", orderCount.getOrderCuisineCountSystem("徽菜"));
		orderCount8.put("name", "徽菜");
		countryOrderCount.put(orderCount1);
		countryOrderCount.put(orderCount2);
		countryOrderCount.put(orderCount3);
		countryOrderCount.put(orderCount4);
		countryOrderCount.put(orderCount5);
		countryOrderCount.put(orderCount6);
		countryOrderCount.put(orderCount7);
		countryOrderCount.put(orderCount8);
		//System.out.println(countryOrderCount.toString());
		for(int i=0;i<countryOrderCount.length();i++)
		{
			//countryOrderCount.get(i)args.toString()
		}
		
	}
	
}

