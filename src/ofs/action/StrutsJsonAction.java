package ofs.action;


import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

public class StrutsJsonAction extends ActionSupport {
    private int i=123;
    private String str="str";
    private int[] array={1,2,3};
    private ArrayList<String> list;
    
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public String getStr() {
        return str;
    }
    public void setStr(String str) {
        this.str = str;
    }
    public int[] getArray() {
        return array;
    }
    public void setArray(int[] array) {
        this.array = array;
    }
    public ArrayList<String> getList() {
        return list;
    }
    public void setList(ArrayList<String> list) {
        this.list = list;
    }
    public String execute(){
        list = new ArrayList<String>();
        list.add("red");
        list.add("green");
        list.add("yellow");
        return SUCCESS;
    }
}