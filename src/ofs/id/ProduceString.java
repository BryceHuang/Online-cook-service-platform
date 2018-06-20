package ofs.id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProduceString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string1="cook_name;cook_rname;cook_password;cook_location;cook_gender;cook_tel;cook_score;cook_workedtime;cook_idnumber;cook_license;cook_healthid;cook_status;cook_desc;cook_money;reg_time;";
		String string2="user_id;user_name;user_rname;user_password;user_gender;user_age;user_location;user_email;user_tel;user_status;user_specific;user_score;user_money;reg_time;";
		String string3="order_id;user_id;cook_id;custom_Name;custom_Place;custom_Tel;is_booked;order_price;order_status;order_kind;order_foodlist;order_remark;order_comment;order_score;start_time;serve_time;insure_time;is_change;change_person;change_person;change_time;change_detail;";
		String string4="cook_idurl;cook_licenseurl;cook_healthurl;";
		String string5="admin_id;admin_name;admin_password;admin_rname;admin_status;admin_level;";
		getJS(string5);
	}

	public static StringBuffer getJS(String names){
		Map<Integer, Object> data = new HashMap<Integer,Object>();  //从string转为map
		int[] location=new int[100];  //记录；所在位置
        int count=0; //记录变量个数
		for(int i=0,j=0;i<names.length();i++)
		{
			if(names.charAt(i)==';')
			{
				data.put(i, names.substring(j, i));
				j=i;
				j++;
				location[count]=j;
				count++;				
			}
			
		}
		System.out.println();		
		System.out.println("---------------------------------------------------------------形式1-------------------------------------");
		for(int i=0;i<count;i++){			
			System.out.print("+row['"+data.get(location[i]-1)+"']");		    

		}	
		System.out.println();	
		System.out.println("----------------------------------------------------------------形式2-----------------------------------------------");

		System.out.println();
		System.out.println("<table>");
		for(int i=0;i<count;i++){	

			String stringName=data.get(location[i]-1).toString();  //每一个变量
			int ilocation=data.get(location[i]-1).toString().indexOf('_');			
			int length=stringName.length();
			String Name=stringName.substring(0, ilocation)+""+stringName.substring(ilocation+1,ilocation+2).toUpperCase()+stringName.substring(ilocation+2, length);
			System.out.println("\t<tr>\r\t\t<td></td>\r\t\t<td><input id="+Name+" name="+Name+" type=\"text\"/></td>\r\t</tr>");	
		}
		System.out.println("</table>");
		System.out.println();
		System.out.println("---------------------------------------------形式3----------------------------------------------");
		System.out.println();
		for(int i=0;i<count;i++){	

			
			String stringName=data.get(location[i]-1).toString();  //每一个变量
			int ilocation=data.get(location[i]-1).toString().indexOf('_');			
			int length=stringName.length();
			String Name=stringName.substring(0, ilocation)+""+stringName.substring(ilocation+1,ilocation+2).toUpperCase()+stringName.substring(ilocation+2, length);
			System.out.println("$(\"#"+Name+"\").val(row['"+stringName+"']);");
		}
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------形式4----------------------------------------------");
		System.out.println();
		for(int i=0;i<count;i++){	

			
			String stringName=data.get(location[i]-1).toString();  //每一个变量
			int ilocation=data.get(location[i]-1).toString().indexOf('_');			
			int length=stringName.length();
			String Name=stringName.substring(0, ilocation)+""+stringName.substring(ilocation+1,ilocation+2).toUpperCase()+stringName.substring(ilocation+2, length);
			System.out.println("String "+Name+" = request.getParameter(\""+Name+"\");");
		}
		System.out.println();
		
		
		System.out.println("---------------------------------------------形式5----------------------------------------------");
		System.out.println();
		for(int i=0;i<count;i++){	

			//	cook.setCook_name(cookName);
			String stringName=data.get(location[i]-1).toString();  //每一个变量
			int ilocation=data.get(location[i]-1).toString().indexOf('_');			
			int length=stringName.length();
			String Name=stringName.substring(0, ilocation)+""+stringName.substring(ilocation+1,ilocation+2).toUpperCase()+stringName.substring(ilocation+2, length);
			String setFunction=stringName.substring(0,1).toUpperCase()+stringName.substring(1,length);
			System.out.println("server.set"+setFunction+"("+Name+");");
		}
		
		return null;
	}
}
