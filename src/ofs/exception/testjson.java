package ofs.exception;


import org.json.JSONArray;
import org.json.JSONObject;


public class testjson {
    public static void main(String[] args) {
        createJson();
    }

    private static void createJson() {
        JSONObject obj = new JSONObject();
        JSONArray array=new JSONArray();
        obj.put("name", "John");
        obj.put("sex", "male");
        obj.put("age", 22);
        obj.put("is_student", true);
        obj.put("hobbies", new String[] {"hiking", "swimming"});
        //调用toString()方法可直接将其内容打印出来
        System.out.println(obj.toString());
    }



}
