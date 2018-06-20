package ofs.id;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class Convert {
	//将list对象转换成jsonString的方法
	public static String convertListtoJsonString(List list){
		JSONArray array=new JSONArray();
		for(int i=0;i<list.size();i++)
		{
			JSONObject object=new JSONObject();
			object=JSONObject.fromObject(list.get(i));
			array.add(object);
		}
		return array.toString();		
	}
	
	//将list对象转换成jsonString的方法
	public static JSONArray convertListtoJsonArray(List list){
		JSONArray array=new JSONArray();
		for(int i=0;i<list.size();i++)
		{
			JSONObject object=new JSONObject();
			object=JSONObject.fromObject(list.get(i));
			array.add(object);
		}
		return array;		
	}


}
