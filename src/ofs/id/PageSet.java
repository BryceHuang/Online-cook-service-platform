package ofs.id;

import java.util.ArrayList;
import java.util.List;

import ofs.javabean.Order;

public class PageSet {
	public List<Order> pageSet(List<Order> list,String limit,String offset){
		List<Order> list2=new ArrayList();
		int limit2=Integer.parseInt(limit);  //页面大小
		int offset2=Integer.parseInt(offset); //第几页
		
			for(int i=limit2*(offset2-1),j=0;i<list.size()&&j<limit2;i++,j++)
			{
				list2.add((Order) list.get(i));
				//System.out.println("    i:"+i+"   j="+j);
			}						
		return list2;
	}

}
